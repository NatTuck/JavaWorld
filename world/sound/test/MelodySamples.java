/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/test/MelodySamples.java                         *
 *                                                                       *
 *   This file is part of JavaWorld.                                     *
 *                                                                       *
 *   JavaWorld is free software: you can redistribute it and/or          *
 *   modify it under the terms of the GNU General Public License         *
 *   as published by the Free Software Foundation, either version        *
 *   3 of the License, or (at your option) any later version.            *
 *                                                                       *
 *   JavaWorld is distributed in the hope that it will be useful,        *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU General Public License for more details.                        *
 *                                                                       *
 *   You should have received a copy of the GNU General Public License   *
 *   along with JavaWorld.  If not, see <http://www.gnu.org/licenses/>.  *
 *                                                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package world.sound.test; 

import world.sound.tunes.SoundConstants;
import java.util.ArrayList;
import java.util.Arrays;

/** Example melodies: Frere-Jaque, and Jeopardy Theme-Song */
public class MelodySamples implements SoundConstants{
    MelodySamples(){}

    public static ArrayList<Integer> frereTune =
        new ArrayList<Integer>(Arrays.asList(
        0,0,0,0,
        NoteC,0,0,0,NoteD,0,0,0,NoteE,0,0,0,NoteC,0,0,0,
        NoteC,0,0,0,NoteD,0,0,0,NoteE,0,0,0,NoteC,0,0,0,
        NoteE,0,0,0,NoteF,0,0,0,NoteG,0,0,0,0,0,0,0,
        NoteE,0,0,0,NoteF,0,0,0,NoteG,0,0,0,0,0,0,0,
        NoteG,0,NoteA,0,NoteG,0,NoteF,0,NoteE,0,0,0,NoteC,0,0,0,
        NoteG,0,NoteA,0,NoteG,0,NoteF,0,NoteE,0,0,0,NoteC,0,0,0,
        NoteC,0,0,0,NoteDownG,0,0,0,NoteC,0,0,0,0,0,0,0,
        NoteC,0,0,0,NoteDownG,0,0,0,NoteC,0,0,0,0,0,0,0,
        0,0,0,0));

    public static ArrayList<Integer> jeopardyTune =
        new ArrayList<Integer>(Arrays.asList(
        0,0,0,0,
        NoteG,0,0,0,NoteUpC,0,0,0,NoteG,0,0,0,NoteC,0,0,0,
        NoteG,0,0,0,NoteUpC,0,0,0,NoteG,0,0,0,0,0,0,0,
        NoteG,0,0,0,NoteUpC,0,0,0,NoteG,0,0,0,NoteUpC,0,0,0,
        NoteUpE,0,0,0,0,0,NoteUpD,0,NoteUpC,0,NoteB,0,NoteA,0,NoteG,0,
        NoteG,0,0,0,NoteUpC,0,0,0,NoteG,0,0,0,NoteC,0,0,0,
        NoteG,0,0,0,NoteUpC,0,0,0,NoteG,0,0,0,0,0,0,0,
        NoteUpC,0,0,0,0,0,NoteA,0,NoteG,0,0,0,NoteF,0,0,0,NoteE,0,0,0,NoteD,0,0,0,NoteC,0,0,0,
        0,0,0,0));
}

