package com.xwtech.miaosha.service.impl;

import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.OrderRepository;
import com.xwtech.miaosha.service.OrderService;
import com.xwtech.miaosha.vo.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderInfo getMiaoshaOrderByUserIdGoodsId(final Integer id, final long goodsId) {
        Specification<OrderInfo> spec = new Specification<OrderInfo>() {

            /**
             * Predicate 封装单个的查询条件
             * Root<Users> root ：查询的属性的封装
             * CriteriaQuery<?> query ：封装了  我们要执行的查询中的各个部分的信息。select   from    order by 等
             * CriteriaBuilder cb ：查询条件的构造器
             */
            @Override
            public Predicate toPredicate(Root<OrderInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("good_id"), goodsId));
                list.add(cb.equal(root.get("user_id"), id));
                Predicate[] arr = new Predicate[list.size()];
                return cb.and(list.toArray(arr));
            }
        };
        List<OrderInfo> orderInfos = this.orderRepository.findAll();
        for (OrderInfo orderInfo:orderInfos) {
            return orderInfo;
        }
        return null;
    }

    @Override
    public OrderInfo createOrder(User user, Goods goods) {
        OrderInfo ol = new OrderInfo();
        ol.setGoodsId(goods.getId());
        ol.setCreateDate(new Date(System.currentTimeMillis()));
        ol.setGoodsName(goods.getGoodsName());
        ol.setGoodsPrice(goods.getMiaoshaPrice());
        ol.setDeliveryAddrId(0l);
        ol.setStatus(0);
        ol.setOrderChannel(1);
        ol.setUserId(new Long((long)user.getId()));
        ol.setGoodsCount(1);
        OrderInfo orderInfo = this.orderRepository.save(ol);
        return orderInfo;
    }

    @Override
    public Pages getOrderList(int size, int page) {
        Pages pages = new Pages();
        final Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate predicate = cb.equal(root.get("goodsName"), "三星mate10");
                return predicate;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(page,size,sort);
        Page all = this.orderRepository.findAll(spec, pageable);
        pages.setSize(all.getSize());
        pages.setCount(all.getTotalPages());
        pages.setData(all.getContent());
        return pages;
    }


}
