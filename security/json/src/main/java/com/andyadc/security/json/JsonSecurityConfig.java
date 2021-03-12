package com.andyadc.security.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

@EnableWebSecurity
public class JsonSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
        ;
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("adc").password("123").roles("user");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            Response resp = Response.success(authentication.getPrincipal());
            writer.write(new ObjectMapper().writeValueAsString(resp));
            writer.flush();
            writer.close();
        });
        filter.setAuthenticationFailureHandler((request, response, e) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            Response resp = Response.fail(e.getMessage());
            writer.write(new ObjectMapper().writeValueAsString(resp));
            writer.flush();
            writer.close();
        });
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
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
            return new Response(Response.CodeEnum.SUCC.getCode(), Response.CodeEnum.SUCC.getMessage(), data);
        }

        public static Response fail(Object data) {
            return new Response(Response.CodeEnum.FAIL.getCode(), Response.CodeEnum.FAIL.getMessage(), data);
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

    class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
            if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
                    || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
                ObjectMapper mapper = new ObjectMapper();
                UsernamePasswordAuthenticationToken authenticationToken = null;
                try (InputStream in = request.getInputStream()) {
                    Map<String, String> map = mapper.readValue(in, Map.class);
                    authenticationToken = new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password"));
                } catch (IOException e) {
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
