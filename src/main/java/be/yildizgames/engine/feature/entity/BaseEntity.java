/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2017 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

import be.yildiz.common.BoundedValue;
import be.yildiz.common.collections.Lists;
import be.yildiz.common.collections.Sets;
import be.yildiz.common.id.ActionId;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.vector.Point3D;
import be.yildizgames.engine.feature.entity.action.Action;
import be.yildizgames.engine.feature.entity.action.NoAction;
import be.yildizgames.engine.feature.entity.data.EntityType;
import be.yildizgames.engine.feature.entity.data.State;
import be.yildizgames.engine.feature.entity.fields.SharedPosition;
import be.yildizgames.engine.feature.entity.fields.StateHolder;
import be.yildizgames.engine.feature.entity.fields.Target;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The entity is the logical data for every usable game object.
 *
 * @author Grégory Van den Borre
 */
public abstract class BaseEntity implements Entity, Target {

    private static final State destroyed = new State("destroyed");

    /**
     * List of all entity visible by this one.
     */
    private final Set<Entity> visibleEntities = Sets.newSet();

    private final Set<PlayerId> seenBy = Sets.newSet();

    /**
     * Entity unique Id, this value is used as an identifier for equals method.
     */
    private final EntityId id;
    /**
     * Position, shared between this entity and its modules.
     */
    private final SharedPosition position = new SharedPosition();
    /**
     * States, shared between this entity and its modules.
     */
    private final StateHolder states = new StateHolder();
    private final BoundedValue energy = new BoundedValue();
    private final BoundedValue hp = new BoundedValue();

    private final EntityType type;

    private String name;

    private PlayerId owner;

    private List<Action> actionRunning = Lists.newList();

    private List<Action> actionComplete = Lists.newList();

    private Action actionToPrepare;

    protected BaseEntity(EntityId id, EntityType type, int hp, int ep) {
        super();
        this.id = id;
        this.type = type;
        this.actionToPrepare = new NoAction(this.id, ActionId.WORLD);
        this.hp.setValue(hp);
        this.energy.setValue(ep);
    }

    @Override
    public void doActions(final long time) {
        this.actionComplete.clear();
        if(!this.actionRunning.isEmpty()) {
            for (Action a : this.actionRunning) {
                if (!a.checkPrerequisite()) {
                    this.actionComplete.add(a);
                } else {
                    boolean running = a.run(time);
                    if (!running) {
                        this.actionComplete.add(a);
                    }
                }
            }
            this.actionRunning.removeAll(this.actionComplete);
        }
    }

    @Override
    public void lookAt(final Point3D destination) {
        this.position.lookAt(destination);
    }

    @Override
    public void addState(final State state) {
        this.states.addState(state);
    }

    @Override
    public void removeState(final State state) {
        this.states.removeState(state);
    }

    @Override
    public boolean hasState(final State state) {
        return this.states.hasState(state);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        return this == o || o instanceof BaseEntity && this.id.equals(((BaseEntity) o).id);
    }

    @Override
    public boolean isSeeing(final Entity e) {
        return this.visibleEntities.contains(e);
    }

    @Override
    public boolean see(final Entity e) {
        return this.visibleEntities.add(e);
    }

    void noLongerSee(final Entity e) {
        this.visibleEntities.remove(e);
    }

    @Override
    public String toString() {
        return "Entity:" + this.getId() + " -- " + this.getType();
    }

    @Override
    public boolean isDeleted() {
        return this.hasState(destroyed);
    }

    @Override
    public EntityType getType() {
        return this.type;
    }

    @Override
    public Point3D getPosition() {
        return this.position.getPosition();
    }

    @Override
    public void setPosition(Point3D position) {
        //FIXME use the module to set the position instead, to avoid to be able to set the position even if the module cannot move.
        assert position != null;
        this.position.setPosition(position);
    }

    @Override
    public Point3D getDirection() {
        return this.position.getDirection();
    }

    @Override
    public void setDirection(Point3D direction) {
        this.position.setDirection(direction);
    }

    @Override
    public List<Action> getActionDone() {
        return this.actionComplete;
    }

    @Override
    public boolean isZeroHp() {
        return this.hp.getValue() == 0;
    }

    @Override
    public int getHitPoints() {
        return this.hp.getValue();
    }

    @Override
    public void setHitPoints(int hitPoint) {
        this.hp.setValue(hitPoint);

    }

    @Override
    public int getMaxHitPoints() {
        return this.hp.getMax();
    }

    @Override
    public int getEnergyPoints() {
        return this.energy.getValue();
    }

    @Override
    public void setEnergyPoints(int energy) {
        this.energy.setValue(energy);
    }

    @Override
    public int getMaxEnergyPoints() {
        return this.energy.getMax();
    }

    @Override
    public List<Action> getActions() {
        return this.actionRunning;
    }

    @Override
    public float getHitPointsRatio() {
        return this.hp.getRatio();
    }

    @Override
    public float getEnergyPointsRatio() {
        return this.energy.getRatio();
    }

    @Override
    public boolean hasSameOwnerAs(final Entity e) {
        return this.owner.equals(e.getOwner());
    }

    @Override
    public void prepareAction(Optional<ActionId> action) {
        this.actionToPrepare = action.map(this::getAction).orElse(new NoAction(this.id, ActionId.WORLD));
    }

    @Override
    public void setOwner(PlayerId ownerId) {
        assert ownerId != null;
        this.owner = ownerId;
    }

    @Override
    public void startPreparedAction() {
        this.startAction(this.actionToPrepare);
    }

    public Set<Entity> getVisibleEntities() {
        return visibleEntities;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final PlayerId getOwner() {
        return owner;
    }

    @Override
    public final List<Action> getActionRunning() {
        return actionRunning;
    }

    public final List<Action> getActionComplete() {
        return actionComplete;
    }

    @Override
    public final Set<PlayerId> getSeenBy() {
        return seenBy;
    }

    @Override
    public final EntityId getId() {
        return id;
    }

    @Override
    public final void setName(String name) {
        this.name = name;
    }

    protected final void addRunningAction(final Action a) {
        this.actionRunning.add(a);
    }
}