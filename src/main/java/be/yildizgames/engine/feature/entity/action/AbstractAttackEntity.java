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

package be.yildizgames.engine.feature.entity.action;

import be.yildiz.common.id.ActionId;
import be.yildiz.common.id.EntityId;
import be.yildiz.common.vector.Point3D;
import be.yildizgames.engine.feature.entity.fields.Target;

/**
 * @author Grégory Van den Borre
 */
public abstract class AbstractAttackEntity extends AbstractAttack {

    private final Follow follow;

    protected Target target;

    public AbstractAttackEntity(final ActionId id, final EntityId attacker, final Move move) {
        super(attacker, id);
        this.follow = new Follow(move, attacker);
        //FIXME distance hardcoded
        this.follow.setDistance(200);
    }

    @Override
    protected final void initImpl() {
        this.follow.init();
    }

    @Override
    public final Point3D getDestination() {
        return this.follow.getDestination();
    }

    @Override
    public final void setDestination(final Point3D destination) {
    }

    @Override
    protected final void runImpl(final long time) {
        if (this.target.isZeroHp()) {
            this.stop();
        }
        this.follow.run(time);
        if (this.position.squaredDistance(this.target.getPosition()) - 1 <= this.range.distance * this.range.distance
                && this.position.getDirection().equals(this.target.getPosition().subtract(this.position.getPosition()))) {
            this.fire();
            if (this.timer.isTimeElapsed()) {
                this.target.hit(this.attackHit);
            }
        } else {
            this.stopFire();
        }
    }

    @Override
    public final void setTarget(final Target target) {
        this.target = target;
        this.follow.setTarget(target);
    }

    @Override
    protected final void stopImpl() {
        this.follow.stop();
        this.stopFire();
    }

    @Override
    public EntityId getTargetId() {
        return this.target.getId();
    }
}
