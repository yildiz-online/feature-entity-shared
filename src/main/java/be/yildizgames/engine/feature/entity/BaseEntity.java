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

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.ActionId;
import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.model.PlayerId;
import be.yildizgames.common.util.BoundedValue;
import be.yildizgames.engine.feature.entity.action.NoAction;
import be.yildizgames.engine.feature.entity.data.EntityType;
import be.yildizgames.engine.feature.entity.data.State;
import be.yildizgames.engine.feature.entity.fields.StateHolder;
import be.yildizgames.engine.feature.entity.fields.Target;
import be.yildizgames.engine.feature.entity.module.EmptyModule;
import be.yildizgames.engine.feature.entity.module.EntityModule;

import java.util.ArrayList;
import java.util.HashSet;
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
    private final Set<Entity> visibleEntities = new HashSet<>();

    private final Set<PlayerId> seenBy = new HashSet<>();

    /**
     * Entity unique Id, this value is used as an identifier for equals method.
     */
    private final EntityId id;
    /**
     * Position, shared between this entity and its modules.
     */
    private Point3D position = Point3D.ZERO;

    private Point3D direction = Point3D.BASE_DIRECTION;

    private Point3D destination = Point3D.ZERO;

    private Target target = null;

    /**
     * States, shared between this entity and its modules.
     */
    protected final StateHolder states = new StateHolder();

    protected final BoundedValue energy = new BoundedValue();

    protected final BoundedValue hp = new BoundedValue();

    private final EntityType type;

    private String name;

    private PlayerId owner;

    protected List<BaseAction> actionRunning = new ArrayList<>();

    protected List<Action> actionComplete = new ArrayList<>();

    protected BaseAction actionToPrepare = new NoAction(EmptyModule.MODULE);

    private final List<EntityModule> modules = new ArrayList<>();

    protected BaseEntity(EntityId id, EntityType type, int hp, int ep) {
        super();
        this.id = id;
        this.type = type;
        this.hp.setValue(hp);
        this.energy.setValue(ep);
    }

    protected BaseEntity(EntityInConstruction e) {
        super();
        this.id = e.getId();
        this.type = e.getType();
        this.hp.setValue(e.getHp());
        this.energy.setValue(e.getEnergy());
    }

    protected final void registerModule(EntityModule m) {
        this.modules.add(m);
    }

    @Override
    public final void doActions(final long time) {
        this.actionComplete.clear();
        for (BaseAction a : this.actionRunning) {
            if (!a.checkPrerequisite(this)) {
                this.actionComplete.add(a);
                this.actionRunning.remove(a);
            } else {
                boolean running = a.run(time, this);
                if (!running) {
                    this.actionComplete.add(a); }
            }
        }
    }

    @Override
    public final void lookAt(final Point3D destination) {
        /*this.position.lookAt(destination);*/
    }

    @Override
    public final void setDestination(Point3D destination) {
        this.destination = destination;
    }

    @Override
    public final void addState(final State state) {
        this.states.addState(state);
    }

    @Override
    public final void removeState(final State state) {
        this.states.removeState(state);
    }

    @Override
    public final boolean hasState(final State state) {
        return this.states.hasState(state);
    }

    @Override
    public final int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        return this == o || o instanceof BaseEntity && this.id.equals(((BaseEntity) o).id);
    }

    @Override
    public final boolean isSeeing(final Entity e) {
        return this.visibleEntities.contains(e);
    }

    @Override
    public final boolean see(final Entity e) {
        return this.visibleEntities.add(e);
    }

    void noLongerSee(final Entity e) {
        this.visibleEntities.remove(e);
    }

    @Override
    public final String toString() {
        return "Entity:" + this.getId() + " -- " + this.getType();
    }

    @Override
    public final boolean isDeleted() {
        return this.hasState(destroyed);
    }

    @Override
    public final EntityType getType() {
        return this.type;
    }

    @Override
    public final Point3D getPosition() {
        return this.position;
    }

    @Override
    public final void setPosition(Point3D position) {
        //FIXME use the module to set the position instead, to avoid to be able to set the position even if the module cannot move.
        assert position != null;
        this.position = position;
    }

    @Override
    public final Point3D getDirection() {
        return this.direction;
    }

    @Override
    public final void setDirection(Point3D direction) {
        this.direction = direction;
    }

    @Override
    public final List<? extends Action> getActionDone() {
        return this.actionComplete;
    }

    @Override
    public final boolean isZeroHp() {
        return this.hp.getValue() == 0;
    }

    @Override
    public final  int getHitPoints() {
        return this.hp.getValue();
    }

    @Override
    public final void setHitPoints(int hitPoint) {
        this.hp.setValue(hitPoint);
    }

    @Override
    public final int getMaxHitPoints() {
        return this.hp.getMax();
    }

    @Override
    public final int getEnergyPoints() {
        return this.energy.getValue();
    }

    @Override
    public final void setEnergyPoints(int energy) {
        this.energy.setValue(energy);
    }

    @Override
    public final int getMaxEnergyPoints() {
        return this.energy.getMax();
    }

    @Override
    public final List<? extends Action> getActions() {
        return this.actionRunning;
    }

    @Override
    public final float getHitPointsRatio() {
        return this.hp.getRatio();
    }

    @Override
    public final float getEnergyPointsRatio() {
        return this.energy.getRatio();
    }

    @Override
    public final boolean hasSameOwnerAs(final Entity e) {
        return this.owner.equals(e.getOwner());
    }

    @Override
    public final void prepareAction(Optional<ActionId> action) {
        this.actionToPrepare = action.map(this::getAction).orElse(new NoAction(EmptyModule.MODULE));
    }

    @Override
    public final void setOwner(PlayerId ownerId) {
        assert ownerId != null;
        this.owner = ownerId;
    }

    @Override
    public final void startPreparedAction() {
        this.startAction(this.actionToPrepare);
    }

    @Override
    public final  boolean hasTarget() {
        return this.target != null && !this.target.isZeroHp();
    }

    @Override
    public final Optional<Target> getTarget() {
        return Optional.ofNullable(this.target);
    }

    public final  Set<Entity> getVisibleEntities() {
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
    public final List<? extends Action> getActionRunning() {
        return actionRunning;
    }

    public final List<? extends Action> getActionComplete() {
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

    @Override
    public final Point3D getDestination() {
        return this.destination;
    }
}