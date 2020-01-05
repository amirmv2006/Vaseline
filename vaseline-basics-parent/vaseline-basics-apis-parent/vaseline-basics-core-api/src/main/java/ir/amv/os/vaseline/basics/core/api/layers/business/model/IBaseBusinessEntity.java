package ir.amv.os.vaseline.basics.core.api.layers.business.model;

import java.util.Objects;

public interface IBaseBusinessEntity<I extends IBaseBusinessModel<I>, M extends IBaseBusinessEntity<I, M>>
		extends IBaseBusinessModel<M> {

	I getId();
	void setId(I id);

	@Override
	default boolean isSameAs(M other) {
		return other != null && Objects.equals(getId(), other.getId());
	}
}
