package com.weini.controller;

import com.weini.POJO.Do.Address;
import com.weini.common.response.Result;
import com.weini.service.AddressService;
import com.weini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/list")
    public Result getAllAddress(@RequestParam String id){
        return addressService.getAllUserAddress(id);
    }

    @GetMapping("/detail")
    public Result getAddressDetail(@RequestBody Address address){
        return addressService.getAddressDetail(address);
    }

    @PostMapping("/add")
    public Result addAddress(@RequestBody Address address){
        return addressService.addUserAddress(address);
    }

    @DeleteMapping("/del")
    public Result deleteAddress(@RequestBody Address address){
        return addressService.deleteUserAddress(address);
    }

    @PutMapping("/update")
    public Result updateAddress(@RequestBody Address address){
        return null;
    }

    @PutMapping("/update/default")
    public Result setDefaultAddress(@RequestBody Address address){
        return addressService.setDefaultAddress(address);
    }
}
