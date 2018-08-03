package com.ashok.auth.services;

import java.util.Map;
import com.ashok.auth.model.User;

public interface SecurityTokenGenerator {

	Map<String, String> generateToken(User user);
}
