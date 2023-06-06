package com.weini.controller;

import com.auth0.jwt.interfaces.Claim;
import com.weini.POJO.Do.Address;
import com.weini.common.response.Result;
import com.weini.service.AddressService;
import com.weini.utils.JwtFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/list")
    public Result getAllAddress(HttpServletRequest request){
        String jwt =request.getHeader("Authorization");
        Map<String, Claim> mes = JwtFactory.getMes(jwt);
        String id = mes.get("id").asString();
        return addressService.getAllUserAddress(id);
    }

    @GetMapping("/detail")
    public Result getAddressDetail(@RequestBody Address address){
        return addressService.getAddressDetail(address);
    }

    @PostMapping("/add")
    public Result addAddress(@RequestBody Address address,HttpServletRequest request){
        String jwt =request.getHeader("Authorization");
        Map<String, Claim> mes = JwtFactory.getMes(jwt);
        String id = mes.get("id").asString();
        address.setUser_id(id);
        return addressService.addUserAddress(address);
    }

    @DeleteMapping("/del")
    public Result deleteAddress(@RequestParam String id){
        return addressService.deleteUserAddress(id);
    }

    @PutMapping("/update")
    public Result updateAddress(@RequestBody Address address){
        return addressService.updateAddress(address);
    }

    @PutMapping("/update/default")
    public Result setDefaultAddress(@RequestBody Address address){
        return addressService.setDefaultAddress(address);
    }
}
