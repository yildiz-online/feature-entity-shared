/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.engine.feature.entity;

import be.yildizgames.common.model.ActionId;

/**
 * An action can be done by an Entity, an Entity can do only one action at a time. 2 actions are considered equals if they share the same Entity. An action can have different states, defined by the
 * combination of 2 booleans:
 * <ul>
 * <li>Prepare to run(init()): toRun is true, running is false.</li>
 * <li>Running(run(time)): toRun is true, running is true.</li>
 * <li>Stopped(stop()): toRun is false, running is false.</li>
 *</ul>
 * @author Grégory Van den Borre
 */
public abstract class BaseAction implements Action {

    /**
     * Passive action will run all the time, and will not notify listeners.
     */
    private final boolean passive;
    /**
     * True if the action is only interacting with the entity itself.
     */
    private final boolean self;
    /**
     * action type, defined by the class.
     */
    public final ActionId id;

    /**
     * <code>true</code> if the action is currently running.
     */
    private boolean running;
    /**
     * <code>true</code> if the action must continue to run.
     */
    private boolean toRun;

    protected BaseAction(final ActionId id, final boolean passive) {
        this(id, passive, false);
    }

    protected BaseAction(final ActionId id, final boolean passive, final boolean self) {
        super();
        this.passive = passive;
        this.self = self;
        this.id = id;
    }


    public final boolean isSelf() {
        return this.self;
    }

    @Override
    public final ActionId getId() {
        return this.id;
    }

    /**
     * @return The used Entity hashCode.
     */
    @Override
    public final int hashCode() {
        return this.id.hashCode();
    }

    /**
     * 2 Actions are considered equals if they share the same Entity, no matter the action identity or state. This is because 1 Entity can only have one action at a time.
     *
     * @param o Object to test for equality.
     * @return <code>true</code> if the test object is an action with the same Entity as this object.
     */
    @Override
    public final boolean equals(final Object o) {
        return o instanceof BaseAction && this.id.equals(((BaseAction) o).id);
    }

    /**
     * Check if 2 Actions are the same type and coming from the same module.
     *
     * @param a Other action to test.
     * @return <code>true</code> if the 2 actions have the same type.
     */
    public final boolean isSameType(final BaseAction a) {
        return this.id.equals(a.id);
    }

    /**
     * Run the action, to stop the action, the stop method must be called, anyway in case of passive action, any call to stop will be ignored, the only way to stop the action is not satisfying the
     * prerequisite condition.
     *
     * @param time Time since the last call.
     * @return <code>true</code> if the action must continue.
     */
    public final boolean run(final long time, Entity e) {
        if (this.passive && this.checkPrerequisite(e)) {
            this.running = true;
            this.runImpl(time, e);
            return true;
        } else if (!this.toRun || !this.checkPrerequisite(e)) {
            this.running = false;
            return false;
        } else {
            this.running = true;
            this.runImpl(time, e);
            return true;
        }
    }

    /**
     * Initialize the action before running it.
     */
    public final void init(Entity e) {
        this.toRun = true;
        this.initImpl(e);
    }

    /**
     * Passive action will run all the time, and will not notify listeners.
     *
     * @return <code>true</code> if this action is passive, <code>false</code> otherwise.
     */
    public final boolean isPassive() {
        return this.passive;
    }

    /**
     * @return <code>true</code> if the action is currently in running state.
     */
    public final boolean isRunning() {
        return this.running;
    }

    /**
     * Run the action.
     *
     * @param time Time since the last call.
     */
    protected abstract void runImpl(long time, Entity e);

    /**
     * Initialize the action before running it.
     */
    protected abstract void initImpl(Entity e);

    /**
     * Stop the action.
     */
    public final void stop(Entity e) {
        this.toRun = false;
        this.running = false;
        this.stopImpl(e);
    }

    protected abstract void stopImpl(Entity e);

    public abstract void delete();
}
