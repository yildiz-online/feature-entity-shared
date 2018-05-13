/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
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

package be.yildizgames.engine.feature.entity.action;

import be.yildizgames.common.model.ActionId;
import be.yildizgames.engine.feature.entity.Entity;
import be.yildizgames.engine.feature.entity.action.materialization.ProtectMaterialization;
import be.yildizgames.engine.feature.entity.fields.AttackHitResult;

/**
 * @author Grégory Van den Borre
 */
public abstract class Protect extends AbstractNoInteractionAction {

    private final ProtectMaterialization materialization;

    protected Protect(final ActionId id, final Entity e, ProtectMaterialization mat) {
        super(id, e, true);
        this.materialization = mat;
    }

    public abstract void addHitResult(AttackHitResult r);

    @Override
    protected final void runImpl(final long time) {
        this.updateHp(time);
        this.updateIfDestroyed(time);
        this.hpReplenishStrategy(time);
    }

    /**
     * Compute Hp after being hit.
     *
     * @param time Time since last call.
     */
    protected abstract void updateHp(long time);

    protected abstract void updateIfDestroyed(long time);

    protected abstract void hpReplenishStrategy(long time);

    @Override
    protected final void stopImpl() {
    }

    @Override
    protected final void initImpl() {
    }

    public final ProtectMaterialization getMaterialization() {
        return this.materialization;
    }
}
