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

package be.yildizgames.engine.feature.entity.action;

import be.yildizgames.engine.feature.entity.BaseAction;
import be.yildizgames.engine.feature.entity.Entity;

/**
 * @author Grégory Van den Borre
 */
public final class Follow extends BaseAction {

    private final Move move;

    private float distance;

    /**
     * Create a new Follow action.
     *
     * @param move   Move action used to follow a target.
     */
    public Follow(final Move move) {
        super(move.id, false);
        this.move = move;
    }

    @Override
    public boolean checkPrerequisite(Entity e) {
        return e.hasTarget();
    }

    @Override
    public void runImpl(final long time, Entity e) {
        e.getTarget().ifPresent(t -> {
            e.setDestination(t.getPosition());
            this.move.run(time, e);
        });
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public void stopImpl(Entity e) {
        this.move.stop(e);
    }

    @Override
    public void initImpl(Entity e) {
        this.move.init(e);
        this.move.setDistance(this.distance);
    }

    @Override
    public void delete() {
        //Unused.
    }
}
