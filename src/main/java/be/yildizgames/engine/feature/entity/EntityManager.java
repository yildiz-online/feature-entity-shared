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

import be.yildizgames.common.model.EntityId;
import be.yildizgames.common.model.PlayerId;
import be.yildizgames.engine.feature.entity.bonus.EntityBonus;
import be.yildizgames.engine.feature.entity.data.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Manage all entities.
 *
 * @author Grégory Van den Borre
 */
public class EntityManager<T extends Entity> {

    /**
     * List of all entities by Player.
     */
    private final Map<PlayerId, Set<T>> entityList = new HashMap<>();

    /**
     * List of all Entity, by Id.
     */
    private final Map<EntityId, T> entities = new HashMap<>();

    /**
     * List of all entity bonus associated to a player.
     */
    private final Map<PlayerId, Set<EntityBonus>> bonusList = new HashMap<>();

    private final T world;

    public EntityManager(T world) {
        this.world = world;
    }

    /**
     * Get the number of entities owned by a player for a given type.
     *
     * @param player Player to get the number of entities.
     * @param type Entity type.
     * @return The number of entities.
     */
    public final int getNumberOfEntities(final PlayerId player, final EntityType type) {
        int number = 0;
        Set<T> l = this.entityList.get(player);
        for (T e : l) {
            if (e.getType() == type) {
                number++;
            }
        }
        return number;
    }

    /**
     * @param player Player to retrieve the entities for.
     * @return All entities owned by a player.
     */
    public final Set<T> getEntities(final PlayerId player) {
        return this.entityList.get(player);
    }

    /**
     * Add an entity. This method is meant to be used automatically when an entity is created, and should not be called manually.
     *
     * @param entity Entity to add.
     */
    public final void addEntity(final T entity) {
        this.entities.put(entity.getId(), entity);
        PlayerId p = entity.getOwner();
        Set<T> list = this.entityList.computeIfAbsent(p, PlayerId -> new HashSet<>());
        list.add(entity);
        //Set<EntityBonus> boni = CollectionUtil.getOrCreateSetFromMap(this.bonusList, p);
        //for (EntityBonus b : boni) {
        // for (EntityType be.yildizgames.engine.feature.entity.data : b.getTypes()) {
        // if (entity.getType().equals(be.yildizgames.engine.feature.entity.data)) {
        // // FIXME *******************************************
        // // entity.addBonus(b);
        // }
        // }
        //}
    }

    /**
     * Remove an entity, this method is meant to be used automatically when an entity is destroyed, and should not be called manually.
     *
     * @param entity Entity to remove.
     */
    //@precondition: entity is existing in this system.
    //@precondition: entity is not nil.
    //@modify: this
    //@affect: entityList, entities.
    //@postcondition: entity is removed from this system.
    public final void removeEntity(final T entity) {
        // FIXME also remove all bonus && visible.
        assert this.entityList.containsKey(entity.getOwner());
        this.entityList.get(entity.getOwner()).remove(entity);
        this.entities.remove(entity.getId());
    }

    public final void removeEntity(EntityId entityId) {
        assert this.entities.get(entityId) != null;
        Optional.ofNullable(this.entities.get(entityId)).ifPresent(this::removeEntity);
    }

    /**
     * Add a bonus to entities.
     *
     * @param p Player that will receive the bonus.
     * @param bonus Bonus to add.
     */
    public final void addBonus(final PlayerId p, final EntityBonus bonus) {
        this.bonusList.computeIfAbsent(p, PlayerId -> new HashSet<>()).add(bonus);
        //Set<Entity> list = CollectionUtil.getOrCreateSetFromMap(this.entityList, p);
        // for (EntityType be.yildizgames.engine.feature.entity.data : bonus.getTypes()) {
        // for (Entity e : list) {
        // if (e.getType().equals(be.yildizgames.engine.feature.entity.data)) {
        // // FIXME ***********************************
        // // e.addBonus(bonus);
        // }
        // }
        // }
    }

    /**
     * Return an Entity from its Id.
     *
     * @param id Id to get.
     * @return The Entity found, or world if nothing is found.
     */
    public final T findById(final EntityId id) {
        return this.entities.getOrDefault(id, this.world);
    }

    /**
     * Update the owner for an Entity
     *
     * @param entity Entity to change the owner.
     * @param player New owner.
     */
    public final void setOwner(final T entity, final PlayerId player) {
        // FIXME bonus not recomputed, needed in gameplay?
        // FIXME recompute bonus in player and remove it from network message
        // parser (and somewhere else?)
        // FIXME builder list not managed, recompute it.
        if (this.entityList.containsKey(player)) {
            this.removeEntity(entity);
        }
        entity.setOwner(player);
        this.addEntity(entity);
    }

    public final List<T> getEntities() {
        return new ArrayList<>(this.entities.values());
    }

    /**
     * @param ids List of ids.
     * @return The entities matching the provided list of id.
     */
    public final List<T> getById(final List<EntityId> ids) {
        return ids.stream()
                .map(this::findById)
                .collect(Collectors.toList());
    }
}
