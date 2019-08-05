package com.th.ox.cleaver.activiti.config.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 * 请求头Request Headers信息由浏览器检测到跨域自动添加，无需过多干预;
 * 重点放在响应头Response headers，它可以帮助我们在服务器进行跨域授权，例如允许哪些原始域可放行，是否需要携带Cookie信息等。
 * 
 * ---------------Request Headers（请求头）-----------------------
 * Origin                                               :表示跨域请求的原始域。
 * Access-Control-Request-Method   :表示跨域请求的方式。（如GET/POST）
 * Access-Control-Request-Headers  :表示跨域请求的请求头信息。
 * 
 * 
 * ------------Response headers（响应头 ）------------------------
 * Access-Control-Allow-Origin           :表示允许哪些原始域进行跨域访问。（字符数组）
 * Access-Control-Allow-Credentials   :表示是否允许客户端获取用户凭据。（布尔类型）
 * 			使用场景：例如现在从浏览器发起跨域请求，并且要附带Cookie信息给服务器。则必须具备两个条件：
 * 			1. 浏览器端：发送AJAX请求前需设置通信对象XHR的withCredentials 属性为true。 
 * 			2.服务器端：设置Access-Control-Allow-Credentials为true。两个条件缺一不可，否则即使服务器同意发送Cookie，浏览器也无法获取。
 * Access-Control-Allow-Methods        :表示跨域请求的方式的允许范围。（例如只授权GET/POST）
 * Access-Control-Allow-Headers         :表示跨域请求的头部的允许范围。
 * Access-Control-Expose-Headers       :表示暴露哪些头部信息，并提供给客户端。（因为基于安全考虑，如果没有设置额外的暴露，跨域的通信对象XMLHttpRequest只能获取标准的头部信息）
 * Access-Control-Max-Age                   :最大缓存时间,浏览器在此时间内再次发起相同请求时，不会再次向服务器请求，而是直接从浏览器缓存中读取
 * 
 */
@Configuration
public class CrossOriginConfig {
	// 解决跨域
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration conf = new CorsConfiguration();

		conf.addAllowedHeader("*");
		conf.addExposedHeader("access-control-allow-headers");

		/*
		 * 允许的请求方法Get/Post/……
		 * 此处允许全部（*），但是在MVC处理时，会根据@GetMapping或@PostMapping对此值进行二次修改
		 */
		conf.addAllowedMethod("*");
		conf.addExposedHeader("access-control-allow-methods");

		/*
		 * 如果在CorsFilter中配置有"Access-Control-Allow-Origin"
		 * 则controller中方法注解@CrossOrigin失效 即filter的优先级高于@CrossOrigin方法注解
		 */
		conf.addAllowedOrigin("*");
		conf.addExposedHeader("access-control-allow-origin");

		/*
		 * 上面只是解决了请求跨域的问题 但是Cookie有时候需要存储到请求地址所在的域中，以便请求页面中的JS进行调用
		 * 此时，请求JS需要XHR的withCredentials 属性为true，代表浏览器中的请求页面所在的域允许Cookie跨域存储
		 * 服务器也需要返回服务器同意发送Cookie的响应头
		 */
		conf.setAllowCredentials(true);
		conf.addExposedHeader("Access-Control-Allow-Credentials");

		/*
		 * 假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回；
		 * 超过这一时间段才进一步由服务器决定是返回新数据还是仍由缓存提供
		 */
		conf.setMaxAge(3600L);
		conf.addExposedHeader("access-control-max-age");

		/*
		 * 跨域访问时，服务器默认只返回简单的响应头 对于自定义的Header，服务器会自动屏蔽，需要手工暴露出来，如下
		 */
		// conf.addExposedHeader("TH-TOKEN-LINYIMART");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", conf); // 4 对接口配置跨域设置
		return new CorsFilter(source);
	}
}
