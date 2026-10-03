# FitZone Pro
 
A single-page gym management app for registering members, collecting fees and finding people quickly. It is the web version of the original `FitZonePro.java` console program, built with plain HTML, CSS and a little JavaScript. No frameworks, no build step.
 
## Run it
 
1. Open `fitzone-pro-v2.html` in any modern browser (double-click it).
2. That's it. There is nothing to install.
The page loads the Archivo font from Google Fonts. If you're offline it falls back to your system font.
 
## Features
 
| Tab | What it does |
|---|---|
| Register | Add a member with name, age, plan and months enrolled. The total due is calculated automatically. |
| Billing | Pick a member, see their pending balance and record a payment. |
| Directory | Browse all members with filter chips (All, Basic, Premium, VIP, Inactive). |
| Edit | Change a member's name, age or plan, or mark them active or inactive. |
| Search | Live, case-insensitive search by any part of a name. |
 
The top of the page shows the member count (out of 100), total pending fees and total collected. The **Fill with demo members** button fills the gym with random sample members so you can see a full directory.
 
## Membership plans
 
| Plan | Fee per month |
|---|---|
| Basic | ₹500 |
| Premium | ₹1000 |
| VIP | ₹2000 |
 
## How it maps to the Java version
 
| Java | Web version |
|---|---|
| Parallel arrays (`memberNames[]`, `totalDue[]`, ...) | One array of member objects |
| `MAX_MEMBERS = 50` | `MAX = 100` |
| Member IDs `M1`, `M2`, ... | Same IDs, shown in lists and dropdowns |
| `Scanner` input and console menu | Forms, tabs and buttons |
| Inactive members can't be edited | Same rule, plus a button to toggle active/inactive (the Java version had no way to set it) |
 
## Known behavior
 
- **Data is kept in memory only.** Refreshing the page clears all members, the same as restarting the Java program.
- **Changing a plan** updates the monthly fee but does not recalculate what the member already owes. This matches the Java version.
- **Payments** can't be edited or undone once recorded.
## Project files
 
- `fitzone-pro-v2.html` – the app (HTML, CSS and JavaScript in one file)
- `FitZonePro.java` – the original console version
## Ideas for later
 
- Save members with `localStorage` so they survive a refresh
- Export the directory to CSV
- Workout logging and fitness reports (these are commented out in the Java menu)
- Delete members and payment history
 
