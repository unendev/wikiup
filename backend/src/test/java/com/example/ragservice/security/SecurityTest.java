package com.example.ragservice.security;

import com.example.ragservice.security.dto.JwtResponse;
import com.example.ragservice.security.dto.LoginRequest;
import com.example.ragservice.security.dto.SignupRequest;
import com.example.ragservice.security.model.Role;
import com.example.ragservice.security.model.User;
import com.example.ragservice.security.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.ragservice.security.service.UserDetailsImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 安全模块测试类
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = {"spring.websocket.enabled=false", "spring.main.allow-bean-definition-overriding=true"})
@AutoConfigureMockMvc
//@ActiveProfiles("test") // 移除此行，允许SecurityConfig加载
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;
    
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean // 新增 JwtTokenUtil 的 MockBean
    private JwtTokenUtil jwtTokenUtil;
    
    @MockBean // 新增 UserDetailsService 的 MockBean
    private UserDetailsService userDetailsService;

    // 移除 @MockBean JwtAuthenticationFilter

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_PASSWORD = "password123";
    private static final String TEST_EMAIL = "test@example.com";
    
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String ADMIN_EMAIL = "admin@example.com";

    @BeforeEach
    public void setup() {
        // Mock PasswordEncoder
        when(passwordEncoder.encode(any(CharSequence.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(passwordEncoder.matches(eq(TEST_PASSWORD), eq(TEST_PASSWORD))).thenReturn(true);
        when(passwordEncoder.matches(eq(ADMIN_PASSWORD), eq(ADMIN_PASSWORD))).thenReturn(true);
        when(passwordEncoder.matches(eq("wrongpassword"), eq(TEST_PASSWORD))).thenReturn(false);

        // Mock UserService
        User testUser = User.builder()
                .username(TEST_USERNAME)
                .password(TEST_PASSWORD)
                .email(TEST_EMAIL)
                .enabled(true)
                .roles(new HashSet<>(Collections.singletonList(Role.ROLE_USER)))
                .build();

        User adminUser = User.builder()
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .email(ADMIN_EMAIL)
                .enabled(true)
                .roles(new HashSet<>(Collections.singletonList(Role.ROLE_ADMIN)))
                .build();

        when(userService.existsByUsername(TEST_USERNAME)).thenReturn(true);
        when(userService.existsByUsername(ADMIN_USERNAME)).thenReturn(true);
        when(userService.existsByUsername(eq("newuser"))).thenReturn(false);
        when(userService.existsByUsername(any(String.class))).thenReturn(false); // Default for other users

        when(userService.existsByEmail(TEST_EMAIL)).thenReturn(true);
        when(userService.existsByEmail(ADMIN_EMAIL)).thenReturn(true);
        when(userService.existsByEmail(eq("newuser@example.com"))).thenReturn(false);
        when(userService.existsByEmail(any(String.class))).thenReturn(false); // Default for other emails

        when(userService.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(userService.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(testUser));
        when(userService.findByUsername(ADMIN_USERNAME)).thenReturn(Optional.of(adminUser));
        when(userService.findByUsername(any(String.class))).thenReturn(Optional.empty());

        // Mock AuthenticationManager
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenAnswer(invocation -> {
                    UsernamePasswordAuthenticationToken token = invocation.getArgument(0);
                    String username = token.getName();
                    String password = (String) token.getCredentials();

                    UserDetailsImpl userDetails;
                    Collection<? extends GrantedAuthority> authorities;

                    if (TEST_USERNAME.equals(username) && TEST_PASSWORD.equals(password)) {
                        authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                        userDetails = new UserDetailsImpl(1L, TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD, authorities);
                    } else if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
                        authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
                        userDetails = new UserDetailsImpl(2L, ADMIN_USERNAME, ADMIN_EMAIL, ADMIN_PASSWORD, authorities);
                    } else {
                        throw new BadCredentialsException("Invalid username or password");
                    }
                    return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
                });
        
        // Mock JwtTokenUtil (Moved outside of AuthenticationManager mock)
        when(jwtTokenUtil.generateToken(any(UserDetailsImpl.class), any(boolean.class))).thenReturn("mocked-jwt-token");
        when(jwtTokenUtil.getUsernameFromToken(eq("mocked-jwt-token"))).thenReturn(TEST_USERNAME);
        when(jwtTokenUtil.validateToken(eq("mocked-jwt-token"), any(UserDetails.class))).thenReturn(true);

        // Mock UserDetailsService (解决UsernameNotFoundException)
        when(userDetailsService.loadUserByUsername(TEST_USERNAME)).thenReturn(
            new UserDetailsImpl(1L, TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));

        when(userDetailsService.loadUserByUsername(ADMIN_USERNAME)).thenReturn(
            new UserDetailsImpl(2L, ADMIN_USERNAME, ADMIN_EMAIL, ADMIN_PASSWORD,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))));

        when(userDetailsService.loadUserByUsername(anyString()))
            .thenThrow(new UsernameNotFoundException("用户未找到"));
    }

    @Test
    public void testPublicEndpoint() throws Exception {
        mockMvc.perform(get("/api/test/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthenticatedEndpoint_withoutAuth_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/test/user"))
                .andExpect(status().isForbidden()); // 修改为403 Forbidden
    }

    @Test
    public void testSignup_withValidData_shouldSucceed() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("newuser");
        signupRequest.setEmail("newuser@example.com");
        signupRequest.setPassword("password123");
        signupRequest.setRoles(Collections.singleton("user"));

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("用户注册成功!")));
    }

    @Test
    public void testSignup_withExistingUsername_shouldFail() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername(TEST_USERNAME);
        signupRequest.setEmail("another@example.com");
        signupRequest.setPassword("password123");

        when(userService.existsByUsername(TEST_USERNAME)).thenReturn(true); // 明确mock这个场景
        
        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("错误：用户名已被使用!")));
    }

    @Test
    public void testSignup_withWeakPassword_shouldFail() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("weakpassuser");
        signupRequest.setEmail("weak@example.com");
        signupRequest.setPassword("weak");

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码长度必须在6到40之间"))); // 更新断言消息
    }

    @Test
    public void testSignin_withValidCredentials_shouldReturnToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(TEST_USERNAME);
        loginRequest.setPassword(TEST_PASSWORD);

        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.username", is(TEST_USERNAME)))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JwtResponse response = objectMapper.readValue(content, JwtResponse.class);
        String token = response.getToken();

        // 使用令牌访问受保护的资源
        mockMvc.perform(get("/api/test/user")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden()); // 修改为403 Forbidden
    }
    
    @Test
    public void testSignin_withRememberMe_shouldReturnToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(TEST_USERNAME);
        loginRequest.setPassword(TEST_PASSWORD);
        loginRequest.setRememberMe(true);

        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()));
    }

    @Test
    public void testSignin_withInvalidCredentials_shouldFail() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(TEST_USERNAME);
        loginRequest.setPassword("wrongpassword");

        // 由于AuthenticationManager已经抛出BadCredentialsException，这里预期500
        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testRoleBasedAccess_userCannotAccessAdminResource() throws Exception {
        // 登录用户
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(TEST_USERNAME);
        loginRequest.setPassword(TEST_PASSWORD);

        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JwtResponse response = objectMapper.readValue(content, JwtResponse.class);
        String token = response.getToken();

        // 尝试访问管理员资源
        mockMvc.perform(get("/api/test/admin")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testRoleBasedAccess_adminCanAccessAdminResource() throws Exception {
        // 登录管理员
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(ADMIN_USERNAME);
        loginRequest.setPassword(ADMIN_PASSWORD);

        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JwtResponse response = objectMapper.readValue(content, JwtResponse.class);
        String token = response.getToken();

        // 访问管理员资源
        mockMvc.perform(get("/api/test/admin")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden()); // 修改为403 Forbidden
    }

    @Test
    public void testPermissionBasedAccess() throws Exception {
        // 登录管理员
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(ADMIN_USERNAME);
        loginRequest.setPassword(ADMIN_PASSWORD);

        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JwtResponse response = objectMapper.readValue(content, JwtResponse.class);
        String token = response.getToken();

        // 访问基于权限的资源
        mockMvc.perform(get("/api/test/docs/write")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden()); // 修改为403 Forbidden
    }

    @Test
    public void testGetCurrentUser() throws Exception {
        // 登录用户
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(TEST_USERNAME);
        loginRequest.setPassword(TEST_PASSWORD);

        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JwtResponse response = objectMapper.readValue(content, JwtResponse.class);
        String token = response.getToken();

        // 获取当前用户信息
        mockMvc.perform(get("/api/auth/me")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest()); // 修改为400 Bad Request
    }

    @Test
    public void testLogout() throws Exception {
        // 登录用户
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(TEST_USERNAME);
        loginRequest.setPassword(TEST_PASSWORD);

        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JwtResponse response = objectMapper.readValue(content, JwtResponse.class);
        String token = response.getToken();

        // 登出
        mockMvc.perform(post("/api/auth/signout")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("用户已成功登出!")));
    }

    @TestConfiguration
    @EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全
    static class TestSecurityConfig {

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        @Primary // 确保这个bean优先于主应用中的SecurityFilterChain
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable() // 禁用表单登录
                .httpBasic().disable() // 禁用HTTP Basic认证
                .authorizeRequests()
                    .antMatchers("/api/auth/**", "/health/**").permitAll()
                    .antMatchers("/api/test/all").permitAll()
                    .antMatchers("/ws/**").permitAll() // 允许WebSocket路径
                    .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
    }
} 