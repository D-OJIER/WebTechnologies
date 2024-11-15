// Define the speed modifiers for each character based on terrain
const speeds = {
    char1: { desert: 0.7, forest: 1, snow: 0.5 },
    char2: { desert: 1, forest: 0.5, snow: 0.7 },
    char3: { desert: 0.6, forest: 0.6, snow: 1.1 }
};

const powerUpEffects = {
    char1: 'VoidedTime',         // Stops other character
    char2: 'FatesGamble',        // Changes terrain and slows them
    char3: 1.3                   // Speed boost
};

// Global variables to manage the terrain sequence and power-ups
let randomTerrainSequence = [];
let powerUps = []; // Stores current power-ups on the track

/**
 * Starts the race by initializing characters, terrain, and power-ups.
 */
function startRace() {
    const raceTrack = document.getElementById("race-track");
    raceTrack.innerHTML = ''; // Clear previous race elements
    document.getElementById("message").textContent = "";
    document.getElementById("WImage").innerHTML = "";
    // Create and append characters to the race track
    const char1 = createCharacter('char1', 50);
    const char2 = createCharacter('char2', 150);
    const char3 = createCharacter('char3', 250);

    raceTrack.appendChild(char1);
    raceTrack.appendChild(char2);
    raceTrack.appendChild(char3);

    // Generate and display the initial random terrain sequence
    randomTerrainSequence = generateRandomTerrainSequence();
    let currentPosition = 0;

    randomTerrainSequence.forEach(terrain => {
        const terrainDiv = document.createElement('div');
        terrainDiv.classList.add('terrain', terrain);
        terrainDiv.style.left = currentPosition + 'px';
        terrainDiv.style.width = '100px';
        raceTrack.appendChild(terrainDiv);
        currentPosition += 100;
    });

    // Generate and display a limited number of power-ups (2 to 4)
    powerUps = generatePowerUps();
    powerUps.forEach(powerUp => raceTrack.appendChild(powerUp));

    // Initialize character positions
    let char1Position = 0, char2Position = 0, char3Position = 0;
    let isRaceOngoing = true;

    /**
     * Animates the race by updating character positions based on terrain and speed.
     */
    function raceAnimation() {
        if (!isRaceOngoing) return;

        // Determine current terrain for each character
        let char1Terrain = getTerrain(char1Position);
        let char2Terrain = getTerrain(char2Position);
        let char3Terrain = getTerrain(char3Position);

        // Calculate speeds based on terrain
        let char1Speed = speeds.char1[char1Terrain];
        let char2Speed = speeds.char2[char2Terrain];
        let char3Speed = speeds.char3[char3Terrain];

        // Check for power-up collections
        checkPowerUpCollection(char1, char1Position, 'char1');
        checkPowerUpCollection(char2, char2Position, 'char2');
        checkPowerUpCollection(char3, char3Position, 'char3');

        // Update character positions
        char1Position += char1Speed;
        char2Position += char2Speed;
        char3Position += char3Speed;

        // Reflect positions on the UI
        char1.style.left = char1Position + "px";
        char2.style.left = char2Position + "px";
        char3.style.left = char3Position + "px";

        // Check for race completion
        if (char1Position >= 800) {
            isRaceOngoing = false;
            document.getElementById("message").textContent = "The one of the night triumphs.";
            document.getElementById("WImage").innerHTML = "<img src='assets/char1.png' width= 300 height= auto>";
        } else if (char2Position >= 800) {
            isRaceOngoing = false;
            document.getElementById("message").textContent = "The lone wanderer wins, for the path was his alone.";
            document.getElementById("WImage").innerHTML = "<img src='assets/char2.png' width= 300 height= auto>";
        } else if (char3Position >= 800) {
            isRaceOngoing = false;
            document.getElementById("message").textContent = "The one with honor wins, for virtue guides the way.";
            document.getElementById("WImage").innerHTML = "<img src='assets/char3.png' width= 300 height= auto>";
        } else {
            requestAnimationFrame(raceAnimation);
        }
    }

    // Start the animation loop
    requestAnimationFrame(raceAnimation);
}

/**
 * Creates a character element with the given ID and vertical position.
 * @param {string} id - The ID of the character.
 * @param {number} topPosition - The vertical position (px) of the character.
 * @returns {HTMLElement} The character DOM element.
 */
function createCharacter(id, topPosition) {
    const char = document.createElement('div');
    char.classList.add('character');
    char.id = id;
    char.style.top = topPosition + 'px';
    return char;
}

/**
 * Generates a random sequence of terrains for the race track.
 * @returns {string[]} An array representing the terrain sequence.
 */
function generateRandomTerrainSequence() {
    const sequence = [];
    const terrains = ['desert', 'forest', 'snow'];
    for (let i = 0; i < 8; i++) {
        sequence.push(terrains[Math.floor(Math.random() * terrains.length)]);
    }
    return sequence;
}

/**
 * Generates a limited number of power-ups (2 to 4) placed at random locations.
 * @returns {HTMLElement[]} An array of power-up DOM elements.
 */
 function generatePowerUps() {
    const xValues = [150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700];
    const yValues = [35, 135, 235];

    // Array of possible power-up image sources
    const powerUpImages = [
        'assets/power1.png',
        'assets/power2.png',
        'assets/power3.png'
    ];

    // Generate 50 random pairs for possible power-up positions
    const possibleLocations = Array.from({ length: 50 }, () => [
        xValues[Math.floor(Math.random() * xValues.length)],
        yValues[Math.floor(Math.random() * yValues.length)]
    ]);

    const numPowerUps = Math.floor(Math.random() * 3) + 2; // Random number between 2 and 4
    const shuffled = shuffleArray([...possibleLocations]);
    const selectedLocations = shuffled.slice(0, numPowerUps);

    // Generate the power-ups using images and randomize the image source
    const generatedPowerUps = selectedLocations.map(([x, y]) => {
        const powerUp = document.createElement('img');  // Create an img element

        // Randomly select an image source
        const randomImage = powerUpImages[Math.floor(Math.random() * powerUpImages.length)];
        powerUp.src = randomImage; 
        powerUp.alt = 'Power Up'; 
        powerUp.style.position = 'absolute';
        powerUp.style.left = `${x}px`;
        powerUp.style.top = `${y}px`; 
        powerUp.style.width = '30px'; 
        powerUp.style.height = '30px';

        document.body.appendChild(powerUp); 
        return powerUp;
    });

    return generatedPowerUps;
}

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
}

function checkPowerUpCollection(char, charPosition, charId) {
    powerUps.forEach((powerUp, index) => {
        const powerUpRect = powerUp.getBoundingClientRect();
        const charRect = char.getBoundingClientRect();
        if (charRect.left < powerUpRect.left + powerUpRect.width &&
            charRect.left + charRect.width > powerUpRect.left &&
            charRect.top < powerUpRect.top + powerUpRect.height &&
            charRect.top + charRect.height > powerUpRect.top) {
            powerUp.remove(); // Remove the collected power-up from the DOM
            powerUps.splice(index, 1); // Remove it from the power-ups array

            // Apply the power-up effect
            applyPowerUpEffect(charId);
        }
    });
}

 function applyPowerUpEffect(charId) {
    if (powerUpEffects[charId] === 'VoidedTime') {
        // Stops down other characters (char2 and char3) for 2.5 seconds
        console.log(`${charId} activated 'VoidedTime' power-up.`);
        speeds.char2.desert *= 0;
        speeds.char2.forest *= 0;
        speeds.char2.snow *= 0;
        speeds.char3.desert *= 0;
        speeds.char3.forest *= 0;
        speeds.char3.snow *= 0;

        setTimeout(() => {
            // Restoring original speeds after 1.5 seconds
            speeds.char2 = { desert: 1, forest: 0.5, snow: 0.7  };
            speeds.char3 = { desert: 0.6, forest: 0.6, snow: 1.1 };
            console.log(`${charId}'s 'VoidedTime' effect has ended.`);
        }, 1500);

        document.getElementById("message").textContent = "Voided Time, Greet the void, for thy fate sealed in stillness";
        document.getElementById("WImage").innerHTML = "<img src='assets/char1.png' width= 300 height= auto>";
        setTimeout(() => {
            document.getElementById("message").textContent = "";
            document.getElementById("WImage").innerHTML = "";
        }, 2000);

    } else if (powerUpEffects[charId] === 'FatesGamble') {
        // Re-randomize the terrain sequence and update the track visually
        console.log(`${charId} activated 'FatesGamble' power-up.`);
        const newTerrainSequence = generateRandomTerrainSequence();
        document.querySelectorAll('.terrain').forEach((terrain, index) => {
            terrain.className = 'terrain ' + newTerrainSequence[index % newTerrainSequence.length];
        });
        // Update the global terrain sequence for race logic
        randomTerrainSequence.length = 0;
        randomTerrainSequence.push(...newTerrainSequence);
        console.log(`Terrain has been re-randomized by ${charId}.`);

        // Pullback effect: slows down other characters temporarily
        const pullbackFactor = -0.3;
        const originalSpeedsChar1 = { ...speeds.char1 };
        const originalSpeedsChar3 = { ...speeds.char3 };

        for (let terrain in originalSpeedsChar1) {
            speeds.char1[terrain] *= pullbackFactor;
            speeds.char3[terrain] *= pullbackFactor;
        }

        document.getElementById("message").textContent = "Cast thy lot, for the world bends to chaos and crumbles in thy wake! Fate's Gamble.";
        document.getElementById("WImage").innerHTML = "<img src='assets/char2.png' width= 300 height= auto>";

        randomTerrainSequence = generateRandomTerrainSequence();

        setTimeout(() => {
            speeds.char1 = originalSpeedsChar1;
            speeds.char3 = originalSpeedsChar3;
            document.getElementById("message").textContent = "";
            document.getElementById("WImage").innerHTML = "";
            console.log(`Pullback effect of ${charId} has ended.`);
        }, 1500);

    } else if (typeof powerUpEffects[charId] === 'number') {
        // Speed boost for the character collecting the power-up
        console.log(`${charId} activated a speed boost power-up.`);
        const boostFactor = powerUpEffects[charId];
        const originalSpeeds = { ...speeds[charId] };

        for (let terrain in originalSpeeds) {
            speeds[charId][terrain] *= boostFactor;
        }
        document.getElementById("message").textContent = `May the blood of mine ancestors awaken within me, for Love and Honor Ancestral Wrath`;
        document.getElementById("WImage").innerHTML = "<img src='assets/char3.png' width= 300 height= auto>";
        setTimeout(() => {
            speeds[charId] = originalSpeeds;
            document.getElementById("message").textContent = '';
            document.getElementById("WImage").innerHTML = "";
            console.log(`${charId}'s speed boost has ended.`);
        }, 1500);
    }
}

function getTerrain(position) {
    const index = Math.floor(position / 100); 
    return randomTerrainSequence[index] || 'desert'; // Default to desert if out of bounds
}
