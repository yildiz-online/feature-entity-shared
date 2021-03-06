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

module be.yildizgames.feature.entity.shared {
    requires be.yildizgames.common.frame;
    requires be.yildizgames.common.model;
    requires be.yildizgames.common.util;
    requires be.yildizgames.common.geometry;
    requires be.yildizgames.common.gameobject;
    requires be.yildizgames.common.time;
    requires be.yildizgames.common.mapping;
    requires be.yildizgames.common.mapping.model;
    requires be.yildizgames.common.mapping.geometry;
    requires be.yildizgames.feature.resource.shared;

    exports be.yildizgames.engine.feature.entity;
    exports be.yildizgames.engine.feature.entity.protocol;
    exports be.yildizgames.engine.feature.entity.protocol.mapper;
    exports be.yildizgames.engine.feature.entity.action;
    exports be.yildizgames.engine.feature.entity.action.materialization;
    exports be.yildizgames.engine.feature.entity.bonus;
    exports be.yildizgames.engine.feature.entity.construction;
    exports be.yildizgames.engine.feature.entity.data;
    exports be.yildizgames.engine.feature.entity.fields;
    exports be.yildizgames.engine.feature.entity.module;
}
