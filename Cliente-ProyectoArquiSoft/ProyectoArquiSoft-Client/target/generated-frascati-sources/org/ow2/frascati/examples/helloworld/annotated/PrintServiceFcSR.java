/*
 * Generated by: org.ow2.frascati.tinfi.opt.oo.ServiceReferenceClassGenerator
 * on: Fri May 31 19:04:02 COT 2019
 */

package org.ow2.frascati.examples.helloworld.annotated;


public class PrintServiceFcSR
extends org.ow2.frascati.tinfi.oasis.ServiceReferenceImpl<org.ow2.frascati.examples.helloworld.annotated.PrintService>
implements org.ow2.frascati.examples.helloworld.annotated.PrintService {

  public PrintServiceFcSR(Class<org.ow2.frascati.examples.helloworld.annotated.PrintService> businessInterface,org.ow2.frascati.examples.helloworld.annotated.PrintService service)  {
    super(businessInterface,service);
  }

  public org.ow2.frascati.examples.helloworld.annotated.PrintService getService()  {
    return this;
  }

  public void print(final java.lang.String arg0)  {
    service.print(arg0);
  }

}
