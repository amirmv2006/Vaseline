package ir.amv.os.vaseline.basics.apis.core.server.base.entity;

import java.io.Serializable;

public interface IBaseEntity<Id extends Serializable> {

	Id getId();
	void setId(Id id);

}
