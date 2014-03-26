package course.cs5300.project1a.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import course.cs5300.project1a.pojo.SessionContent;
import course.cs5300.project1a.pojo.SessionID;
import course.cs5300.project1a.service.LocalSessionTableManager;

@Named
@Scope("singleton")
public class LocalSessionTableManagerImpl implements LocalSessionTableManager {

	@Override
	public SessionID addSession(SessionContent sessionContent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSession(SessionID sessionId, SessionContent sessionContent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SessionContent getSession(SessionID sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSession(SessionID sessionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeExpiredSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeSessionLocally(SessionID sessionId,
			SessionContent sessionContent) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	// 以下是过期code

//	private static Map<Long, SessionContent> sessionMap = new HashMap<Long, SessionContent>();
//	private static long currentSessionId = 0;
//
//	@Override
//	public synchronized long addSession(SessionContent sessionContent) {
//		// TODO Auto-generated method stub
//		long returnVal = currentSessionId++;
//		try {
//			sessionMap.put(returnVal, (SessionContent) sessionContent.clone());
//		} catch (CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return returnVal;
//	}
//
//	@Override
//	public synchronized void updateSession(long sessionId,
//			SessionContent sessionContent) {
//		// TODO Auto-generated method stub
//		try {
//			sessionMap.put(sessionId, (SessionContent) sessionContent.clone());
//		} catch (CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public synchronized SessionContent getSession(long sessionId) {
//		// TODO Auto-generated method stub
//		SessionContent returnVal = sessionMap.get(sessionId);
//		if (returnVal != null) {
//			try {
//				returnVal = (SessionContent) returnVal.clone();
//			} catch (CloneNotSupportedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return returnVal;
//	}
//
//	@Override
//	public synchronized void removeSession(long sessionId) {
//		// TODO Auto-generated method stub
//		sessionMap.remove(sessionId);
//	}
//	
//	@Override
//	@Scheduled(initialDelay=45000, fixedRate=45000)
//	@Async
//	public synchronized void removeExpiredSession() {
//		// TODO Auto-generated method stub
//		System.out.println("Removing Expired Session  <<-------------------------------------");
//		Timestamp currentTs = new Timestamp((new Date()).getTime());
//		Iterator<Entry<Long, SessionContent>> iter = this.sessionMap.entrySet().iterator();
//		while(iter.hasNext()){
//			Entry<Long, SessionContent> entry = iter.next();
//			SessionContent sessionContent = entry.getValue();
//			if(sessionContent.getExpirationTimestamp().before(currentTs)){
//				// ---------------------------------------------------
//				System.out.println(entry.getKey() + "Expire Time" + entry.getValue().getExpirationTimestamp().toString() + " current time " + currentTs.toString() + " removed from session table <<------------------------------");
//				// ---------------------------------------------------
//				iter.remove();
//			}
//		}
//	}

}
