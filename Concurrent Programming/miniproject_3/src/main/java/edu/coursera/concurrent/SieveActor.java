package edu.coursera.concurrent;

import edu.rice.pcdp.Actor;

import java.util.ArrayList;
import java.util.List;

import static edu.rice.pcdp.PCDP.async;
import static edu.rice.pcdp.PCDP.finish;

/**
 * An actor-based implementation of the Sieve of Eratosthenes.
 *
 * TODO Fill in the empty SieveActorActor actor class below and use it from
 * countPrimes to determine the number of primes <= limit.
 */
public final class SieveActor extends Sieve {

    private static List<Integer> primeNumbers = new ArrayList();
    /**
     * {@inheritDoc}
     *
     * TODO Use the SieveActorActor class to calculate the number of primes <=
     * limit in parallel. You might consider how you can model the Sieve of
     * Eratosthenes as a pipeline of actors, each corresponding to a single
     * prime number.
     */
    @Override
    public int countPrimes(final int limit) {
        primeNumbers.clear();
        List<Integer> numberList = new ArrayList();
        for (int i = 2; i <= limit; i++) {
            numberList.add(i);
        }
        SieveActorActor actor = new SieveActorActor();
        finish(() -> {
            actor.send(numberList);
        });
        return primeNumbers.size();
    }

    /**
     * An actor class that helps implement the Sieve of Eratosthenes in
     * parallel.
     */
    public static final class SieveActorActor extends Actor {
        /**
         * Process a single message sent to this actor.
         *
         * TODO complete this method.
         *
         * @param msg Received message
         */
        @Override
        public void process(final Object msg) {
             List<Integer> numberList = (ArrayList) msg;
             if (numberList.size() == 0)
                 return;
             int firstNumber = numberList.get(0);
             primeNumbers.add(firstNumber);
             for (int i = 0; i < numberList.size(); i++){
                 if (numberList.get(i) % firstNumber == 0){
                     numberList.remove(i);
                 }
             }
             SieveActorActor actor = new SieveActorActor();
             actor.send(numberList);
        }
    }
}
