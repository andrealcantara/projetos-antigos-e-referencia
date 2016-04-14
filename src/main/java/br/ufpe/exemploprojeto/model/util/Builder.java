package br.ufpe.exemploprojeto.model.util;

import java.util.function.Supplier;

import com.google.common.base.Preconditions;

public interface Builder{
	
	static Object of(Supplier<? extends Object> suprir) {
		Object e = suprir.get();
		Preconditions.checkNotNull(e);
		return suprir.get();
	}
}
