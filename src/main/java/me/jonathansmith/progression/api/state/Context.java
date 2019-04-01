package me.jonathansmith.progression.api.state;

import me.jonathansmith.progression.api.actor.Actor;

import java.util.List;

/**
 * It is assumed that this user_interface represents the wider context for a state. Essentially encapsulating either a state or
 * a more refined context for a state.
 *
 * A context should describe a coordinate system - allowing quick extraction of a state at a given state index
 *
 * A context should also be able to refine a 'view' of itself based on area properties that represents a navigable, interact-able subset of the states for an actor
 */
public interface Context<T extends StateIndex> {

    T getStatePointer(int x, int y, int z);

    List getChildContexts();

    State getStateAt(T statePointer);

    boolean isRootContextForActor(Actor actor);
}
