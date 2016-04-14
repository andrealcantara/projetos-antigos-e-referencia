package main;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class HelperCDI {
	private static Weld weld;
	private static WeldContainer container;

	private HelperCDI() {
		weld = new Weld();
		container = weld.initialize();
	}
	
	public HelperCDI getInstance(){
		return HelperCDIHolder.HOLDER;
	}
	
	
	public <T> T getClass(Class<T> clazz){
		return container.instance().select(clazz).get();
	}
	
	private static class HelperCDIHolder{
		private static HelperCDI HOLDER = new HelperCDI();
	}

}
