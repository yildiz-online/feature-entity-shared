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

package be.yildizgames.engine.feature.entity.action.materialization;

import be.yildizgames.common.gameobject.GameMaterialization;
import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.EntityId;

/**
 * @author Grégory Van den Borre
 */
public class EmptyProtectMaterialization implements ProtectMaterialization{

    /**
     * Associated entity id.
     */
    private final EntityId id;

    public EmptyProtectMaterialization(EntityId id) {
        super();
        this.id = id;
    }

    @Override
    public void destroy() {
        //does nothing
    }

    @Override
    public GameMaterialization getObject() {
        return new GameMaterialization() {
            @Override
            public EntityId getId() {
                return id;
            }

            @Override
            public void rotate(float x, float y, float z, float w) {
                //does nothing
            }

            @Override
            public Point3D getScaleSize() {
                return Point3D.valueOf(1);
            }

            @Override
            public void scale(float x, float y, float z) {
                //does nothing
            }

            @Override
            public void delete() {
                //does nothing
            }

            @Override
            public void sleep(boolean b) {
                //does nothing
            }

            @Override
            public void attachTo(Movable other) {
                //does nothing
            }

            @Override
            public void addChild(Movable other) {
                //does nothing
            }

            @Override
            public void removeChild(Movable child) {
                //does nothing
            }

            @Override
            public Movable getInternal() {
                return this;
            }

            @Override
            public void attachToOptional(Movable other) {
                //does nothing
            }

            @Override
            public void detachFromParent() {
                //does nothing
            }

            @Override
            public Point3D getPosition() {
                return Point3D.ZERO;
            }

            @Override
            public void setPosition(Point3D newPosition) {
                //does nothing
            }

            @Override
            public Point3D getAbsolutePosition() {
                return Point3D.ZERO;
            }

            @Override
            public Point3D getDirection() {
                return Point3D.BASE_DIRECTION;
            }

            @Override
            public void setDirection(Point3D newDirection) {
                //does nothing
            }

            @Override
            public Point3D getAbsoluteDirection() {
                return Point3D.BASE_DIRECTION;
            }

            @Override
            public void setPosition(float posX, float posY, float posZ) {
                //does nothing
            }

            @Override
            public void setDirection(float dirX, float dirY, float dirZ) {
                //does nothing
            }

            @Override
            public void addOptionalChild(Movable child) {
                //does nothing
            }
        };
    }
}
