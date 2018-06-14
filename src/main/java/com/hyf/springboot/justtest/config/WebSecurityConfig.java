package com.hyf.springboot.justtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    MyUserDetailService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/browser/index.html#/")
                .failureUrl("/login?error")
                .permitAll() //登录页面用户任意访问

                .and().headers().frameOptions().sameOrigin()//允许同源frame

                .and()
                //开启cookie保存用户数据
                .rememberMe()
                //设置cookie有效期
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                //设置cookie的私钥
                .key("asd")

                .and()
                .logout()
                //默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/logout")
                //设置注销成功后跳转页面，默认是跳转到登录页面
                .logoutSuccessUrl("/index")
                .permitAll()

                //Spring Security4默认是开启CSRF的，所以需要请求中包含CSRF的token信息，
                // 在其官方文档（参考资料1）中，提供了在form中嵌入一个hidden标签来获取token信息，
                // 其原理是，hidden标签使用了Spring Security4提供的标签，即${_csrf.parameterName}、${_csrf.token}，
                // 后台页面渲染过程中，将此标签解所对应的值解析出来，这样，我们的form表单，就嵌入了Spring Security的所需的token信息，
                // 在后续的提交登录请求时，就不会出现没有CSRF token的异常。

                //另外，还有一个解决办法是，通过关闭CSRF来解决，
                // 这个几乎在任何场景中都能解决这个问题（上面这个解决方案，可能在某些渲染模板不能解析出来token值，
                // 不过可以通过后台程序来获取token值，然后自己定义变量来渲染到form中，这个也是可以的）。
                // 具体的做法是通过修改配置文件来关闭，我这里使用的是SpringBoot开发的项目，
                // 配置文件直接写在配置类中，通过.csrf().disable()来关闭，参考资料见二。
                // 不过这种方案，会迎来CSRF攻击，不建议在生产环境中使用，如果系统对外界做了隔离，这样做也是可以的。

                //在from表单中加入一个hidden标签，来引用spring security  的csrf token。
                //<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
                .and()
                .csrf().disable() //关闭CSRF
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
        //防止拦截css,js，image文件
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
        //登录验证，绑定自定义的UserDetailServiceHolder
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
