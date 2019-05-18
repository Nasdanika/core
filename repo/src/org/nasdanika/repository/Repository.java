package org.nasdanika.repository;

/**
 * Repository contains resources identified by their paths.
 * Repository resources are operated on by commands passed to executors.
 * @author Pavel Vlasov
 *
 */
public interface Repository {
	
	/**
	 * @return Unauthenticated executor or null if such unauthenticated command execution is not supported.
	 */
	Executor createExecutor();
	
	/**
	 * @param user
	 * @param password
	 * @return Creates an executor authenticated by user name and password.
	 */
	Executor createExecutors(String login, String password) throws AuthenticationException;

	/**
	 * @param identity Login for pre-authenticated user or an authorization token if <code>jsonWebToken</code> is <code>true</code>.
	 * @param jsonWebToken If true then the identity is treated as a JWT token.  
	 * @return An executor for pre-authenticated identity.
	 * @throws AuthenticationException
	 */
	Executor createExecutor(String identity, boolean jsonWebToken) throws AuthenticationException;

}
