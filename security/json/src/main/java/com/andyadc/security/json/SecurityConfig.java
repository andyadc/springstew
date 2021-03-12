package com.andyadc.security.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("adc").password("123").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginProcessingUrl("/dologin")
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(401);
                    PrintWriter writer = response.getWriter();
                    Response resp = new Response("401", "SC_UNAUTHORIZED");
                    writer.write(MAPPER.writeValueAsString(resp));
                    writer.flush();
                    writer.close();
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        http.addFilterAt(jsonLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JSONLoginFilter jsonLoginFilter() throws Exception {
        JSONLoginFilter jsonLoginFilter = new JSONLoginFilter();
        jsonLoginFilter.setAuthenticationManager(authenticationManagerBean());
        jsonLoginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                Response resp = Response.success(authentication.getPrincipal());
                writer.write(MAPPER.writeValueAsString(resp));
                writer.flush();
                writer.close();
            }
        });
        jsonLoginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                Response resp = Response.fail(e.getMessage());
                writer.write(MAPPER.writeValueAsString(resp));
                writer.flush();
                writer.close();
            }
        });
        return jsonLoginFilter;
    }

    static class Response {
        private String code;
        private String message;
        private Object data;

        public Response() {
        }

        public Response(String code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public Response(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static Response success(Object data) {
            return new Response(CodeEnum.SUCC.getCode(), CodeEnum.SUCC.getMessage(), data);
        }

        public static Response fail(Object data) {
            return new Response(CodeEnum.FAIL.getCode(), CodeEnum.FAIL.getMessage(), data);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        enum CodeEnum {
            SUCC("000", "success"), FAIL("-1", "fail");
            String code;
            String message;

            CodeEnum(String code, String message) {
                this.code = code;
                this.message = message;
            }

            public String getCode() {
                return code;
            }

            public String getMessage() {
                return message;
            }
        }
    }

    class JSONLoginFilter extends UsernamePasswordAuthenticationFilter {
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
            if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
                    || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
                UsernamePasswordAuthenticationToken authenticationToken = null;
                try (InputStream inputStream = request.getInputStream()) {
                    Map<String, String> map = MAPPER.readValue(inputStream, Map.class);
                    String username = map.get("username");
                    String password = map.get("password");
                    authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setDetails(request, authenticationToken);
                return this.getAuthenticationManager().authenticate(authenticationToken);
            } else {
                return super.attemptAuthentication(request, response);
            }
        }
    }
}
