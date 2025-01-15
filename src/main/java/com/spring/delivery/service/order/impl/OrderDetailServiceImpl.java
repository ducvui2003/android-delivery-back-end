package com.spring.delivery.service.order.impl;

import com.spring.delivery.domain.request.order.RequestOrderDetailCreated;
import com.spring.delivery.domain.response.order.OrderDetailDTO;
import com.spring.delivery.domain.response.order.OrderDetailResponse;
import com.spring.delivery.domain.response.product.ProductDTO;
import com.spring.delivery.domain.response.promotion.PromotionDTO;
import com.spring.delivery.mapper.OrderDetailMapper;
import com.spring.delivery.mapper.PromotionMapper;
import com.spring.delivery.repository.mysql.OrderDetailRepository;
import com.spring.delivery.service.business.product.IProductService;
import com.spring.delivery.service.order.OrderDetailService;
import com.spring.delivery.service.promotion.PromotionService;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper orderDetailMapper;
    PromotionMapper promotionMapper;
    OrderDetailRepository orderDetailRepository;
    PromotionService promotionService;
    IProductService productService;


    @Override
    public OrderDetailResponse getOrderDetailById(Long id) {
        var orderDetail = orderDetailRepository.findById(id).orElseThrow(() -> new AppException(AppErrorCode.ORDER_DETAIL_NOT_FOUND));

        Optional<List<ProductDTO>> productDTOs = Optional.ofNullable(Optional.of(orderDetail.getProductIds()
                        .stream().map(productService::findById).toList())
                .orElseThrow(() -> new AppException(AppErrorCode.PRODUCT_NOT_FOUND)));

        Optional<Set<PromotionDTO>> promotionDTOs = Optional.of(
                orderDetail.getPromotionIds().stream().map(promotionService::getPromotion).collect(Collectors.toSet())
        );

        OrderDetailResponse res = orderDetailMapper.toOrderDetailResponse(orderDetail);
        res.setPromotions(promotionMapper.toPromotionOrderResponse(promotionDTOs.get()));
        res.setProductDTOList(productDTOs.get());
        return res;
    }

    @Override
    public RequestOrderDetailCreated addOrderDetail(RequestOrderDetailCreated req) {
        log.info("order detail  " );
        var orderDetail = orderDetailRepository.save(orderDetailMapper.toOrderDetail(req));
        log.info("order detail: {} " ,orderDetail );
        return orderDetailMapper.toOrderDetailCreated(orderDetail);
    }
}
