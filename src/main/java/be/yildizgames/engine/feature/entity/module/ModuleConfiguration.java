/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildizgames.engine.feature.entity.module;

import be.yildiz.common.id.PlayerId;
import be.yildizgames.engine.feature.entity.data.EntityType;

/**
 * Simple object to store information about a custom entity module set.
 *
 * @author Grégory Van den Borre
 */
public final class ModuleConfiguration {

    /**
     * Module set name.
     */
    private final String name;

    /**
     * Player creating the module set.
     */
    private final PlayerId player;

    /**
     * Associated entity type.
     */
    private final EntityType type;

    /**
     * List of module to use in the set.
     */
    private final ModuleGroup modules;

    public ModuleConfiguration(String name, PlayerId player, EntityType type, ModuleGroup modules) {
        this.name = name;
        this.player = player;
        this.type = type;
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public PlayerId getPlayer() {
        return player;
    }

    public EntityType getType() {
        return type;
    }

    public ModuleGroup getModules() {
        return modules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModuleConfiguration that = (ModuleConfiguration) o;

        return name.equals(that.name) && player.equals(that.player) && type.equals(that.type) && modules.equals(that.modules);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + modules.hashCode();
        return result;
    }
}
