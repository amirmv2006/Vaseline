package ir.amv.os.vaseline.basics.core.api.server.base.entity;

import java.io.Serializable;

public interface IBaseEntity<Id extends Serializable> {

	Id getId();
	void setId(Id id);

}
