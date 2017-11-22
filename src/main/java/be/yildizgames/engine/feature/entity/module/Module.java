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

import be.yildiz.common.BoundedValue;
import be.yildiz.common.id.ActionId;
import be.yildizgames.engine.feature.entity.action.Action;
import be.yildizgames.engine.feature.entity.fields.SharedPosition;
import be.yildizgames.engine.feature.entity.fields.StateHolder;

/**
 * A module is a part of an Entity, it contains its specific action.
 *
 * @author Grégory Van den Borre
 */
public class Module<A extends Action> {

    /**
     * action associated to the module.
     */
    private final A action;

    protected Module(A action) {
        super();
        this.action = action;
    }

    /**
     * Delete the wrapped action object.
     *
     */
    //@Ensures call this.action.delete()
    public final void delete() {
        this.action.delete();
    }

    public final void setSharedPosition(SharedPosition position) {
        this.action.setSharedPosition(position);
    }

    public final void setStates(final StateHolder states) {
        this.action.setStates(states);
    }

    public final void setEnergy(final BoundedValue energy) {
        this.action.setEnergy(energy);
    }

    public final void setHp(final BoundedValue hp) {
        this.action.setHp(hp);
    }

    /**
     * @return The module associated Id.
     */
    public final ActionId getId() {
        return this.action.id;
    }

    public final A getAction() {
        return action;
    }
}
