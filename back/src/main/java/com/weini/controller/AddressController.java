package com.weini.controller;

import com.weini.common.response.Result;
import com.weini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class AddressController {
    @Autowired
    UserService userService;



}
