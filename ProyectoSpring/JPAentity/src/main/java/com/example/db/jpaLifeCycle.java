package com.example.db;


import com.example.entidades.Expense;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@ApplicationScoped
@Transactional
public class jpaLifeCycle {

    @PersistenceContext
    private EntityManager em;

    public void testPersist(){
        // Transient
        Expense expense = new Expense();
        expense.setAmount(new BigDecimal("9.99"));
        expense.setConcept("lifecycle test");
        expense.setDate(LocalDate.now());
        log.info("Expense transient {}", expense);

        // Persist
        em.persist(expense);
        log.info("Expense despues de persist {}", expense);

        // getId
        Expense expenseInContext = em.find(Expense.class, expense.getId());
        log.info("Expense in context {}", expense);

        // Flush
        expense.setConcept("entity is managed");
        em.flush();
        log.info("Expense despues de flush {}", expense);


    }

    public void testRemove(){
        Expense expenseRemove = em.find(Expense.class, 3L);
        em.remove(expenseRemove);
        log.info("Expense despues de remove {}", expenseRemove);
        Expense expenseFindAfterRemove =  em.find(Expense.class, 3L);
        log.info("Expense find despues de remove {}", expenseFindAfterRemove);
        em.flush();

    }

}
