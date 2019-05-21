package co.premier.zuul.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import co.premier.zuul.entities.LogEntity;
import co.premier.zuul.repository.ILogRepository;

@Component
public class PostFilter extends ZuulFilter {

	@Autowired
	private ILogRepository logRepository;

	private static final Logger log = LoggerFactory.getLogger(PostFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
			   final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
			   ctx.setResponseBody(responseData);
			   LogEntity logEntity = new LogEntity();
			   logEntity.setBody(ctx.getResponseBody());
			   logEntity.setMetodo("N/A");
			   logEntity.setTipo("Respuesta");
			   logEntity.setUrl(request.getRequestURL().toString());
			   logRepository.save(logEntity);
			} catch (IOException e) {
			   log.warn("Error reading body",e);
			}
		log.info("Ingresando al filtro post");
		System.out.println("entra al filtro de post");
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

}
