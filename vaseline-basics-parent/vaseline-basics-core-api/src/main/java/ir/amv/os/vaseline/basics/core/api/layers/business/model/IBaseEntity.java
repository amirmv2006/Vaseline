package ir.amv.os.vaseline.basics.core.api.layers.business.model;

import java.util.Objects;

public interface IBaseEntity<I, M extends IBaseEntity<I, M>>
		extends IBaseModel<M> {

	I getIdentity();

	@Override
	default boolean isSameAs(M other) {
		return other != null && Objects.equals(getIdentity(), other.getIdentity());
	}
}
