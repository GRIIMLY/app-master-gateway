package co.premier.zuul.filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import co.premier.zuul.entities.LogEntity;
import co.premier.zuul.repository.ILogRepository;

public class PreFilter  extends ZuulFilter{
	
	@Autowired
	public ILogRepository logRepository;
	
	
	
	private static Logger log = LoggerFactory.getLogger(PreFilter.class);
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    System.out.println("entra a prefilter " +  request.getHeader("Authorization"));
	    //Obtenemos toda la informacion del request body
	    LogEntity logEntity = new LogEntity();
	     try {
	    	 log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
			String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			logEntity.setBody(body);
			logEntity.setMetodo(request.getMethod());
			logEntity.setTipo("Petición");
			logEntity.setUrl(request.getRequestURL().toString());
			logRepository.save(logEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	
		
}
