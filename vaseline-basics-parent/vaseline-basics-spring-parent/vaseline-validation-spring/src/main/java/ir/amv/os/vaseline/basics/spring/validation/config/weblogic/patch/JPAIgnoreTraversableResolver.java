package ir.amv.os.vaseline.basics.spring.validation.config.weblogic.patch;

import javax.validation.Path;
import javax.validation.TraversableResolver;
import java.lang.annotation.ElementType;

/**
 * when loaded in weblogic, validator uses JPA by default.
 */
public class JPAIgnoreTraversableResolver implements TraversableResolver {

   @Override
   public boolean isReachable(Object traversableObject,
         Path.Node traversableProperty, Class<?> rootBeanType,
         Path pathToTraversableObject, ElementType elementType) {
      return true;
   }

   @Override
   public boolean isCascadable(Object traversableObject,
         Path.Node traversableProperty, Class<?> rootBeanType,
         Path pathToTraversableObject, ElementType elementType) {
      return true;
   }

}