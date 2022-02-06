/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.biblioteka.so;

import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;

/**
 *
 * @author korisnik
 */
public abstract class AbstractSO {

    public Object execute(Object param) throws Exception {
        try {
            precondition(param);
            startTransaction();
            Object returnParam = executeOperation(param);
            comitTransaction();
            return returnParam;
        } catch (Exception exception) {
            rollbackTransaction();
            throw exception;
        }

    }

    protected abstract void precondition(Object param) throws Exception;

    protected abstract Object executeOperation(Object param) throws Exception;

    private void startTransaction() {
    }

    protected void comitTransaction() throws Exception {
        DbConnectionFactory.getInstance().getConnection().commit();

    }

    protected void rollbackTransaction() throws Exception {
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
