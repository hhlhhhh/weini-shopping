package com.weini.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weini.POJO.Do.User;
import com.weini.mapper.UserMapper;
import com.weini.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
