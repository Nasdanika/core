/**
 * This package contains adapters for generating customer view of the {@link org.nasdanika.bank.Bank} model.
 * For example in the model accounts belong to the bank (containment {@link org.eclipse.emf.ecore.EReference}s) as well as transactions.
 * However, from the customer standpoint accounts belong to them and transactions belong to statements.
 * 
 * In the future this package shall be moved to org.nasdanika.bank.web bundle to be used for static HTML generation and by
 * a bank web application (to be created). 
 */
package org.nasdanika.html.tests.adapters.customer;

