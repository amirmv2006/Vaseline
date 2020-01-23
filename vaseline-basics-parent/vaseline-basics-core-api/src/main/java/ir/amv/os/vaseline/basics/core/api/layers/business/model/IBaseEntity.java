package ir.amv.os.vaseline.basics.core.api.layers.business.model;

import java.util.Objects;

public interface IBaseEntity<I extends IBaseModel<I>, M extends IBaseEntity<I, M>>
		extends IBaseModel<M> {

	I getId();
	void setId(I id);

	@Override
	default boolean isSameAs(M other) {
		return other != null && Objects.equals(getId(), other.getId());
	}
}
