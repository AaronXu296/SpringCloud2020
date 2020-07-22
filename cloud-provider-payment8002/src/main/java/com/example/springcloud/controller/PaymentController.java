package com.example.springcloud.controller;

import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果："+result);

        if (result>0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort, result);
        }else {
            return new CommonResult(444,"插入数据库失败");
        }

    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果："+payment);

        if(payment!=null){
            return new CommonResult<Payment>(200,"查询成果,serverPort:"+serverPort, payment);
        }else {
            return new CommonResult(444,"没有对应记录，查询id："+id);
        }

    }

}
