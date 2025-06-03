/*
 * PLAN
 * -------------------------------------------------------------------------
 *
 * CORE MECHANICS:
 * Central Hub/Main Room:
 *    - Grand chamber of Ancient Pyramid with 3 passages:
 *      1. Chamber of the Sun God
 *      2. Hall of the Moon Priestess
 *      3. Chamber of the Eclipse
 *
 * Location Behavior:
 *    - First visit: Unique description and key piece (Rooms 1-2)
 *    - Repeat visits: Alternate description
 *    - Vault (Room 3): Locked until both key halves are combined
 *
 * VARIABLES:
 *    - boolean visitedSunChamber, visitedMoonHall, visitedEclipseChamber
 *    - boolean hasSunKeyHalf, hasMoonKeyHalf
 *
 * PROGRAM FLOW:
 *    start loop:
 *        1. Display central hub description and passage options (1/2/3/quit)
 *        2. Player chooses a passage or quits early
 *            3. Room 1: Chamber of the Sun God
 *               - First visit: Obtain sun key half and first message
 *               - Repeat: Message about "already visiting" or something similar
 *            4. Room 2: Hall of the Moon Priestess
 *               - First visit: Obtain moon key half and first message
 *               - Repeat: Message about "already visiting" or something similar
 *            5. Room 3: Chamber of the Eclipse
 *               - Locked: Show locked message
 *               - Unlocked: Show unlocked message, then exit game
 *    end loop
 */

public class AdventureGame {
}
