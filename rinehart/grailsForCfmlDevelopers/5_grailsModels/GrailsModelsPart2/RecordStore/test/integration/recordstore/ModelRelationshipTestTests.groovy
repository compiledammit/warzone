package recordstore

import static org.junit.Assert.*
import org.junit.*

class ModelRelationshipTestTests {

// NOTE: this one will fail due do eventualy addition of belongsTo
@Test
void songShouldHaveAnAlbum() {
	// Create and save an album
	def album = new Album( name: "Emotionalism" ).save()
	
	// Create a song without an album: shoudn't be valid
	def song = new Song( name: "Salvation Song" )
	
	// Shouldn't validate, shouldn't have an id
	assert !song.validate()
	assert !song.id
	
	// Set its album: should validate and save
	song.album = album
	assert song.validate()
	song.save()
	assert song.id
}

@Test
void albumHasManySongShouldCascadeSaves() {
	def originalSongCount = Song.list().size()
	
	// Create and save an album
	def album = new Album( name: "Emotionalism" ).save()
	
	// Create a song without an album: shoudn't be valid
	def song = new Song( name: "Salvation Song" )
	
	album.addToSongs( song )

	// References should be set up
	assert song.album == album
	assert album.songs.contains( song )
	
	// Saving album should save song
	assert !song.id
	album.save()
	assert Song.list().size() == originalSongCount + 1
	assert song.id
}

@Test
void settingSongsAlbumDoesntAddToAlbumsSongs() {
	// Create and save an album
	def album = new Album( name: "Emotionalism" ).save()
	
	// Create a song without an album: shoudn't be valid
	def song = new Song( name: "Salvation Song" )
	
	song.album = album

	// Back references should not be set up
	assert song.album == album
	assert album.songs == null
}

@Test
void songBelongsToAlbumShouldCascadeDeletes() {
	def originalSongCount = Song.list().size()
	
	// Create and save an album
	def album = new Album( name: "Emotionalism" )
	def song = new Song( name: "Salvation Song" )
	
	album.addToSongs( song )
	
	// Saving an album should also save its songs
	assert !album.id
	assert !song.id
	album.save()
	assert album.id
	assert song.id
	
	// Deleting an album should delete its songs
	album.delete()
	assert Song.list().size() == originalSongCount
}

@Test
void songsWithinAnAlbumAreOrdered() {
	def originalSongCount = Song.list().size()
	
	// Create and save an album
	def album = new Album( name: "Emotionalism" )
	def song = new Song( name: "Shame" )
	def song2 = new Song( name: "Die Die Die" )
	
	album.addToSongs( song )
	album.addToSongs( song2 )
	
	// Lists have an innate and reliable order
	def tmp = album.songs.get( 0 )
	album.songs[0] = album.songs[1]
	album.songs[1] = tmp
	
	assert album.songs[0] == song2
	assert album.songs[1] == song
	
}

@Test
void weCanQueryForSongsViaFindersOrHQL() {
	// Create and save an album
	def album = new Album( name: "Emotionalism" )
	def song = new Song( name: "Die Die Die" )
	def song2 = new Song( name: "Shame" )
	
	album.addToSongs( song )
	album.addToSongs( song2 )
	album.save()
	
	// Dynamic finder
	assert Song.findAllByAlbum( album ).size() == 2
	
	// HQL
	assert Song.executeQuery( "from Song s where s.album = ?", [ album ] ).size() == 2
}

}



