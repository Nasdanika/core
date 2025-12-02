package org.nasdanika.common;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Realm protects access to the realm objects.
 * It may provide some isolation of commands passed to it and it may
 * provide access control such as disabling writes in read commands or
 * hiding features to create a command "bounded context".
 */
public interface Realm {
	
	<T,R> R read(T element, BiFunction<Realm, T, R> command);		
	
	default <T,R> CompletionStage<R> readAsync(T element, BiFunction<Realm, T, R> command) {
		return CompletableFuture.completedStage(read(element, command));
	}
	
	default <T,R> Mono<R> readReactive(T element, BiFunction<Realm, T, R> command) {
		return Mono.just(read(element, command));
	}
		
	default <T,R> Flux<R> readReactiveMany(T element, BiFunction<Realm, T, R> command) {
		return Flux.just(read(element, command));
	}
	
	// Consumer flavor
	
	default <T> void read(T element, BiConsumer<Realm, T> command) {
		BiFunction<Realm, T, Void> c = (r,e) -> {
			command.accept(r, e);
			return null;
		};		
		read(element, c);
	}
	
	default <T> CompletionStage<Void> readAsync(T element, BiConsumer<Realm, T> command) {
		BiFunction<Realm, T, Void> c = (r,e) -> {
			command.accept(r, e);
			return null;
		};		
		return CompletableFuture.completedStage(read(element, c));
	}
	
	default <T> Mono<Void> readReactive(T element, BiConsumer<Realm, T> command) {
		BiFunction<Realm, T, Void> c = (r,e) -> {
			command.accept(r, e);
			return null;
		};		
		return Mono.just(read(element, c));
	}
	
	// No element flavor
		
	default <R> R read(Function<Realm,R> command) {
		BiFunction<Realm, Void, R> c = (r,e) -> {
			return command.apply(r);
		};		
		return read(null, c);
	}		
	
	default <R> CompletionStage<R> readAsync(Function<Realm, R> command) {
		return CompletableFuture.completedStage(read(command));
	}
	
	default <R> Mono<R> readReactive(Function<Realm, R> command) {
		return Mono.just(read(command));
	}
		
	default <R> Flux<R> readReactiveMany(Function<Realm, R> command) {
		return Flux.just(read(command));
	}
	
	// No element consumer flavor
	
	default void read(Consumer<Realm> command) {
		Function<Realm, Void> c = (r) -> {
			command.accept(r);
			return null;
		};		
		read(c);
	}
	
	default CompletionStage<Void> readAsync(Consumer<Realm> command) {
		Function<Realm, Void> c = (r) -> {
			command.accept(r);
			return null;
		};		
		return CompletableFuture.completedStage(read(c));
	}
	
	default Mono<Void> readReactive(Consumer<Realm> command) {
		Function<Realm, Void> c = (r) -> {
			command.accept(r);
			return null;
		};		
		return Mono.just(read(c));
	}		
	
	
	<T,R> R write(T element, BiFunction<Realm, T, R> command);		
	
	default <T,R> CompletionStage<R> writeAsync(T element, BiFunction<Realm, T, R> command) {
		return CompletableFuture.completedStage(write(element, command));
	}
	
	default <T,R> Mono<R> writeReactive(T element, BiFunction<Realm, T, R> command) {
		return Mono.just(write(element, command));
	}
		
	default <T,R> Flux<R> writeReactiveMany(T element, BiFunction<Realm, T, R> command) {
		return Flux.just(write(element, command));
	}
	
	// Consumer flavor
	
	default <T> void write(T element, BiConsumer<Realm, T> command) {
		BiFunction<Realm, T, Void> c = (r,e) -> {
			command.accept(r, e);
			return null;
		};		
		write(element, c);
	}
	
	default <T> CompletionStage<Void> writeAsync(T element, BiConsumer<Realm, T> command) {
		BiFunction<Realm, T, Void> c = (r,e) -> {
			command.accept(r, e);
			return null;
		};		
		return CompletableFuture.completedStage(write(element, c));
	}
	
	default <T> Mono<Void> writeReactive(T element, BiConsumer<Realm, T> command) {
		BiFunction<Realm, T, Void> c = (r,e) -> {
			command.accept(r, e);
			return null;
		};		
		return Mono.just(write(element, c));
	}
	
	// No element flavor
		
	default <R> R write(Function<Realm,R> command) {
		BiFunction<Realm, Void, R> c = (r,e) -> {
			return command.apply(r);
		};		
		return write(null, c);
	}		
	
	default <R> CompletionStage<R> writeAsync(Function<Realm, R> command) {
		return CompletableFuture.completedStage(write(command));
	}
	
	default <R> Mono<R> writeReactive(Function<Realm, R> command) {
		return Mono.just(write(command));
	}
		
	default <R> Flux<R> writeReactiveMany(Function<Realm, R> command) {
		return Flux.just(write(command));
	}
	
	// No element consumer flavor
	
	default void write(Consumer<Realm> command) {
		Function<Realm, Void> c = (r) -> {
			command.accept(r);
			return null;
		};		
		write(c);
	}
	
	default CompletionStage<Void> writeAsync(Consumer<Realm> command) {
		Function<Realm, Void> c = (r) -> {
			command.accept(r);
			return null;
		};		
		return CompletableFuture.completedStage(write(c));
	}
	
	default Mono<Void> writeReactive(Consumer<Realm> command) {
		Function<Realm, Void> c = (r) -> {
			command.accept(r);
			return null;
		};		
		return Mono.just(write(c));
	}		
	
	
	/**
	 * Realm element
	 * @param <T>
	 */
	interface Element<T> extends Supplier<T> {
		
		Realm getRealm();
		
		default <R> R read(BiFunction<Realm, T, R> command) {
			return getRealm().read(get(), command);
		}
		
		default <R> CompletionStage<R> readAsync(BiFunction<Realm, T, R> command) {
			return getRealm().readAsync(get(), command);
		}
		
		default <R> Mono<R> readReactive(BiFunction<Realm, T, R> command) {
			return getRealm().readReactive(get(), command);
		}
			
		default <R> Flux<R> readReactiveMany(BiFunction<Realm, T, R> command) {
			return getRealm().readReactiveMany(get(), command);
		}
		
		// Consumer flavor
		
		default void read(BiConsumer<Realm, T> command) {
			getRealm().read(get(), command);
		}
		
		default CompletionStage<Void> readAsync(BiConsumer<Realm, T> command) {
			return getRealm().readAsync(get(), command);
		}
		
		default Mono<Void> readReactive(BiConsumer<Realm, T> command) {
			return getRealm().readReactive(get(), command);
		}


		
		
		
		default <R> R write(BiFunction<Realm, T, R> command) {
			return getRealm().write(get(), command);
		}
		
		default <R> CompletionStage<R> writeAsync(BiFunction<Realm, T, R> command) {
			return getRealm().writeAsync(get(), command);
		}
		
		default <R> Mono<R> writeReactive(BiFunction<Realm, T, R> command) {
			return getRealm().writeReactive(get(), command);
		}
			
		default <R> Flux<R> writeReactiveMany(BiFunction<Realm, T, R> command) {
			return getRealm().writeReactiveMany(get(), command);
		}
		
		// Consumer flavor
		
		default void write(BiConsumer<Realm, T> command) {
			getRealm().write(get(), command);
		}
		
		default CompletionStage<Void> writeAsync(BiConsumer<Realm, T> command) {
			return getRealm().writeAsync(get(), command);
		}
		
		default Mono<Void> writeReactive(BiConsumer<Realm, T> command) {
			return getRealm().writeReactive(get(), command);
		}
		
	}
	
}
