package edu.coursera.concurrent;

import edu.rice.pcdp.Actor;

import java.util.ArrayList;
import java.util.List;

import static edu.rice.pcdp.PCDP.finish;

/**
 * An actor-based implementation of the Sieve of Eratosthenes.
 *
 * TODO Fill in the empty SieveActorActor actor class below and use it from
 * countPrimes to determine the number of primes <= limit.
 */
public final class SieveActor extends Sieve {
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
        int numberOfPrimes = 0;
        SieveActorActor actor = new SieveActorActor(2);
        finish(() -> {
            for (int i = 3; i <= limit; i += 2) {
                actor.send(i);
            }
        });

        SieveActorActor loopActor = actor;
        while (loopActor != null){
            numberOfPrimes += loopActor.numLocalPrimes;
            loopActor = loopActor.nextActor;
        }
        return numberOfPrimes;
    }

    /**
     * An actor class that helps implement the Sieve of Eratosthenes in
     * parallel.
     */
    public static final class SieveActorActor extends Actor {
        private final int MAX_NUMBER_OF_PRIMES = 1000;
        private SieveActorActor nextActor;
        private List<Integer> locallyPrimeNumbers = new ArrayList(MAX_NUMBER_OF_PRIMES);
        private int numLocalPrimes;

        SieveActorActor(int localPrime){
            locallyPrimeNumbers.add(localPrime);
            this.nextActor = null;
            numLocalPrimes = 1;
        }
        /**
         * Process a single message sent to this actor.
         *
         * TODO complete this method.
         *
         * @param msg Received message
         */
        @Override
        public void process(final Object msg) {
            Integer number = (Integer) msg;
            if (locallyPrime(number)) {
                if (numLocalPrimes < MAX_NUMBER_OF_PRIMES) {
                    locallyPrimeNumbers.add(number);
                    numLocalPrimes++;
                } else if (nextActor == null) {
                    nextActor = new SieveActorActor(number);
                } else {
                    nextActor.send(number);
                }
            }
        }

        private boolean locallyPrime(int number) {
            for (Integer locallyPrimeNumber: locallyPrimeNumbers){
                if (number % locallyPrimeNumber == 0){
                    return false;
                }
            }
            return true;
        }
    }
}
