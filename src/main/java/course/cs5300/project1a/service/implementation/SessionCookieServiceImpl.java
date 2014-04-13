package course.cs5300.project1a.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import course.cs5300.project1a.pojo.SessionContent;
import course.cs5300.project1a.service.GetLocalIPService;
import course.cs5300.project1a.service.SessionCookieService;
import course.cs5300.project1a.service.LocalSessionTableManager;
import course.cs5300.project1a.pojo.SessionID;
import course.cs5300.project1a.service.*;

@Named
public class SessionCookieServiceImpl implements SessionCookieService {

	private static final String COOKIE_NAME = "CS5300PROJ1SESSIONBYSW773";

	private static final long cookieExpirationTimeInSec = 30;

	@Inject
	private GetLocalIPService getLocalIPService;
	
	@Inject
	private LocalSessionTableManager sessionStateTableManager;

	@Override
	public SessionID createSession(HttpServletResponse response,
			Timestamp currentTimestamp, long version) {
		// TODO Auto-generated method stub
		Timestamp expirationTS = new Timestamp(currentTimestamp.getTime()
				+ cookieExpirationTimeInSec * 1000);
		SessionContent sessionContent = new SessionContent("Hello User",
				version, expirationTS);
		SessionID sessionId =  new SessionID(-1,getLocalIPService.getLocalIP());//this.sessionStateTableManager.addSession(sessionContent);
		return sessionId;
	}

	public void updateSession(long sessionId, HttpServletResponse response,
			Timestamp currentTimestamp, long version) {
		// TODO Auto-generated method stub
		SessionContent sessionContent = this.sessionStateTableManager
				.getSession(null);
		if (sessionContent != null) {
			Timestamp expirationTS = new Timestamp(currentTimestamp.getTime()
					+ cookieExpirationTimeInSec * 1000);
			sessionContent.setExpirationTimestamp(expirationTS);
			sessionContent.setVersion(version);
			this.sessionStateTableManager.updateSession(null,
					sessionContent);
			this.writeSessionInfoToCookie(response, sessionId, version, 0, 0);
		}
	}

	public void updateSessionMessage(SessionID sessionId, String message) {
		SessionContent sessionContent = this.sessionStateTableManager
				.getSession(null); // null = sessionId
		if (sessionContent != null) {
			sessionContent.setMessage(message);
			this.sessionStateTableManager.updateSession(null,
					sessionContent);
		}
	}

	public void deleteSession(long sessionId, HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.sessionStateTableManager.removeSession(null);
		removeCookie(response);
	}

	@Override
	public SessionID getSessionId(HttpServletRequest request) {
		String cookieVal = this.getCookieVal(request);
		if (cookieVal != null) {
			Scanner scanner = new Scanner(cookieVal).useDelimiter("_");
			System.out.println(cookieVal);
			SessionID sessionId = new SessionID(scanner.nextLong(),getLocalIPService.getLocalIP());
			scanner.close();
			return sessionId;
		}
		return new SessionID(-1,getLocalIPService.getLocalIP());
	}

	@Override
	public String getCookieVal(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				if (name.equalsIgnoreCase(COOKIE_NAME)) {
					return value;
				}
			}
		}
		return null;
	}

	private void writeSessionInfoToCookie(HttpServletResponse response,
			long sessionId, long version, long expiration, long metadata) {
		String cookieValue = sessionId + "_" + version + "_" + expiration + "_"
				+ metadata;
		Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
		cookie.setMaxAge((int) cookieExpirationTimeInSec);
		response.addCookie(cookie);
		// ---------------------------------------------------
		System.out
				.println("cookie added to response <<------------------------------");
		// ---------------------------------------------------
	}

	private void removeCookie(HttpServletResponse response) {
		String cookieValue = "removed";
		Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		// ---------------------------------------------------
		System.out.println("cookie removed <<------------------------------");
		// ---------------------------------------------------
	}

	public void updateSessionMessage(long sessionId, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSession(SessionID sessionId,
			HttpServletResponse response, Timestamp currentTimestamp,
			long version) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSession(SessionID sessionId, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
