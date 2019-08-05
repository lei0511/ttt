package com.th.ox.cleaver.activiti.config.mvc;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

	/**
	 * 文件临时存储目录(unix)
	 */
	@Value("${file.save-path-unix}")
	private String rootPathUnix;
	/**
	 * 文件临时存储目录(windows)
	 */
	@Value("${file.save-path-windows}")
	private String rootPathWindows;

	/**
	 * 文件存储路径
	 */
	@Value("${file.static-access-path}")
	private String fileAccessPath;

	/**
	 * 为MVC定制Object的默认转换器
	 * 
	 * A：解析浏览器的请求JSON数据为对象 B：将返回对象类型转为JSON字符串
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		// 序列化配置
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(
				// 输出key时是否使用双引号
				QuoteFieldNames,
				// 是否输出值为null的字段
				WriteMapNullValue,
				// 数值字段如果为null,输出为0,而非null
				WriteNullNumberAsZero,
				// List字段如果为null,输出为[],而非null
				WriteNullListAsEmpty,
				// 字符类型字段如果为null,输出为"",而非null
				WriteNullStringAsEmpty,
				// Boolean字段如果为null,输出为false,而非null
				WriteNullBooleanAsFalse,
				// WriteNullStringAsEmpty,// null String不输出
				// WriteMapNullValue,//null String也要输出
				// Date的日期转换器
				WriteDateUseDateFormat,
				// 禁止循环引用
				DisableCircularReferenceDetect
		);
		converter.setFastJsonConfig(config);
		converters.add(converter);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? rootPathWindows : rootPathUnix;
		registry.addResourceHandler(fileAccessPath + "/**").addResourceLocations("file:" + path);
	}

}