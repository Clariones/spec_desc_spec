/*
	ChangeRequestBaseHandler 最基础的Handler，如果这些ChangeRequest都按照Event规则来处理，
	那么有这个ChangeRequestBaseHandler对于大多数系统就足够了。
*/
package  com.cla.sds.changerequest;
import java.util.Arrays;
import com.cla.sds.changerequest.ChangeRequest;
import com.cla.sds.SdsUserContext;
import com.cla.sds.CustomSdsCheckerManager;
public class ChangeRequestChainProcessor extends CustomSdsCheckerManager{
	
	protected void preProcess(SdsUserContext userContext, ChangeRequest request, String beanName){
		
	}
	public void process(SdsUserContext userContext, ChangeRequest request, String beanName){
		preProcess(userContext, request, beanName);
		processInternal(userContext, request, beanName);
		postProcess(userContext, request, beanName);
	}
	protected void postProcess(SdsUserContext userContext, ChangeRequest request, String beanName){
		
	}
	protected void processInternal(SdsUserContext userContext, ChangeRequest request, String beanName){
		
	}
	
}




