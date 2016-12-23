package br.com.geradorOkaeri.cloudinary;
 
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
 
/**
 * This listener disables a text field with a given ID when it contains a specific text.
 * @author itcuties
 */
public class FieldDisableListener implements PhaseListener {
 
    private static final long serialVersionUID = -7607159318721947672L;
 
    // The phase where the listener is going to be called
//    private PhaseId phaseId = PhaseId.RENDER_RESPONSE;
    private PhaseId phaseId = PhaseId.ANY_PHASE;
    private int count = 0;
 
    /**
     * Recursively walk through the view tree, 
     */
    public void beforePhase(PhaseEvent event) {
//        processViewTree(event.getFacesContext().getViewRoot()); 
    }
     
    public void afterPhase(PhaseEvent event) {
        // Do nothing here  
//    	System.out.println(++count + " -> " +event.getPhaseId());
    }
 
    public PhaseId getPhaseId() {
        return phaseId;
    }
     
    private void processViewTree(UIComponent component) {
//        old_process_view_tree(component);
    }

	private void old_process_view_tree(UIComponent component) {
		// Go to every child
        for (UIComponent child: component.getChildren()) {
            // Display component ID and its type
            System.out.println("+ " + child.getId() + " ["+child.getClass()+"]");
             
            // If component has a given ID then check if you can hide it
            if ("dummy-text-id".equals(child.getId())) {
                // Get the input text
                HtmlInputText inputText     = (HtmlInputText)child;
                // Get input text value
                String  inputTextValue  = (String)inputText.getValue();
                 
                // Hide the field when its value match the "hide me!" string
                if ("hide me!".equals(inputTextValue))
                    inputText.setRendered(false);
                 
                // Disable the field
                if ("disable me!".equals(inputTextValue))
                    inputText.setReadonly(true);
             
            }
             
            // Process next node
            processViewTree(child);
        }
	}
     
}