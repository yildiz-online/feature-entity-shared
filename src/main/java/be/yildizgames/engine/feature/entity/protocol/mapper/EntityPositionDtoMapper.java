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

package be.yildizgames.engine.feature.entity.protocol.mapper;

import be.yildizgames.common.mapping.EntityIdMapper;
import be.yildizgames.common.mapping.MappingException;
import be.yildizgames.common.mapping.ObjectMapper;
import be.yildizgames.common.mapping.Point3DMapper;
import be.yildizgames.common.mapping.Separator;
import be.yildizgames.engine.feature.entity.protocol.EntityPositionDto;

/**
 * @author Grégory Van den Borre
 */
public class EntityPositionDtoMapper implements ObjectMapper<EntityPositionDto> {

    private static final EntityPositionDtoMapper INSTANCE = new EntityPositionDtoMapper();

    private EntityPositionDtoMapper() {
        super();
    }

    public static EntityPositionDtoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public EntityPositionDto from(String s) throws MappingException {
        assert s != null;
        String[] v = s.split(Separator.OBJECTS_SEPARATOR );
        try {
            return new EntityPositionDto(EntityIdMapper.getInstance().from(v[0]),
                    Point3DMapper.getInstance().from(v[1]),
                    Point3DMapper.getInstance().from(v[2]));
        } catch (final IndexOutOfBoundsException e) {
            throw new MappingException(e);
        }
    }

    @Override
    public String to(EntityPositionDto dto) {
        assert dto != null;
        return EntityIdMapper.getInstance().to(dto.id)
                + Separator.OBJECTS_SEPARATOR
                + Point3DMapper.getInstance().to(dto.position)
                + Separator.OBJECTS_SEPARATOR
                + Point3DMapper.getInstance().to(dto.orientation);
    }
}
