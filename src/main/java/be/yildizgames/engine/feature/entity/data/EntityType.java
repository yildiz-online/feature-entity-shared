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

package be.yildizgames.engine.feature.entity.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple wrapper class for an entity type. An Entity type is composed of a type
 * and a name, both must be unique in the application instance.
 * Immutable class.
 * @author Grégory Van den Borre
 *
 * specfield type : int : positive not null value, 2 different types cannot
 * have the same type value.
 * specfield name : String : not null value, 2 different types cannot have the
 * same name.
 */
public final class EntityType {

    /**
     * Map the type to its index value.
     */
    private static final Map<Integer, EntityType> MAP = new HashMap<>();

    /**
     * Constant for the world type.
     */
    public static final EntityType WORLD = new EntityType(0, "world");

    /**
     * Index value.
     */
    public final int type;

    /**
     * Entity type name.
     */
    public final String name;

    /**
     * Full constructor.
     *
     * @param value Entity type index, must be unique.
     * @param name  Type name, must be unique.
     */
    //@requires value >=0 && value != null && !EntityType.MAP.containsKey(value);
    //@requires name != null && !name.empty && !EntityType.MAP.containsValue(name);
    //@ensures EntityType.MAP.get(value) == this;
    //@ensures this.type = value;
    //@ensures this.name = name;
    public EntityType(final int value, final String name) {
        super();
        assert !EntityType.MAP.containsKey(value) : "Type already exists";

        this.name = name;
        this.type = value;
        EntityType.MAP.putIfAbsent(value, this);
        assert this.invariant();
    }

    /**
     * Retrieve a type from its index.
     *
     * @param index Entity index value.
     * @return The Type matching the index value.
     */
    public static EntityType valueOf(final int index) {
        assert EntityType.MAP.containsKey(index) : "Entity type " + index + " not registered";
        return EntityType.MAP.getOrDefault(index, WORLD);
    }

    @Override
    public String toString() {
        return this.name;
    }

    private boolean invariant() {
        assert this.type >= 0 : "Type must be positive";
        assert this.name != null : "Name must not be null";
        assert EntityType.MAP.get(this.type) == this : "This object is not registered";
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityType that = (EntityType) o;

        return type == that.type && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + name.hashCode();
        return result;
    }
}
